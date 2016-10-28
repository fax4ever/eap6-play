package it.redhat.demo.service;

import it.redhat.demo.NumberApi;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.concurrent.*;

/**
 * @author Fabio Massimo Ercoli
 *         fabio.ercoli@redhat.com
 *         on 13/07/16
 */
public class EjbClient {

    public static final int INVOCATION_NUMBER = 1000;
    public static final int POOL_SIZE = 10;

    public static void main(String[] args) throws Exception {

        NumberApi numberApi = lookup();
        ArrayList<Future<Integer>> futures = new ArrayList<>();

        Callable<Integer> task = () -> {
            return numberApi.getNext();
        };

        ExecutorService executor = Executors.newFixedThreadPool(POOL_SIZE);

        for (int i = 0; i< INVOCATION_NUMBER; i++) {
            futures.add(executor.submit(task));
        }

        for (int i = 0; i< INVOCATION_NUMBER; i++) {

            try {

                Integer integer = futures.get(i).get(100, TimeUnit.SECONDS);
                System.out.println("Riceve integer value :: " + i + " :: " + integer);

            } catch (Exception ex) {

                System.out.println("Exception :: " + ex.getMessage());

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
