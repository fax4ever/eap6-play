package it.redhat.demo.service;

import it.redhat.demo.NumberApi;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author Fabio Massimo Ercoli
 *         fabio.ercoli@redhat.com
 *         on 13/07/16
 */
public class EjbClient {

    public static final int INT = 1000;

    public static void main(String[] args) throws Exception {

        NumberApi numberApi = lookup();
        ArrayList<Future<Integer>> futures = new ArrayList<>();

        Callable<Integer> task = () -> {
            return numberApi.getNext();
        };

        ExecutorService executor = Executors.newFixedThreadPool(INT);

        for (int i = 0; i< INT; i++) {
            futures.add(executor.submit(task));
        }

        for (int i = 0; i< INT; i++) {

            try {

                Integer integer = futures.get(i).get();
                System.out.println("Eccoci :: " + i + " :: " + integer);

            } catch (Exception ex) {

                System.out.println("Eccoci :: " + ex.getMessage());

            }

        }

        executor.shutdown();

    }

    public static NumberApi lookup() throws NamingException {

        final Hashtable<String, String> jndiProperties = new Hashtable();
        jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        final Context context = new InitialContext(jndiProperties);
        return (NumberApi) context.lookup("ejb:/eap6-ejb-remote/NumberApiBean!it.redhat.demo.NumberApi");

    }

}
