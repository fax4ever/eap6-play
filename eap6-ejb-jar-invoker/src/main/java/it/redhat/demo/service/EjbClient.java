package it.redhat.demo.service;

import it.redhat.demo.NumberApi;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;

/**
 * @author Fabio Massimo Ercoli
 *         fabio.ercoli@redhat.com
 *         on 13/07/16
 */
public class EjbClient {

    public static void main(String[] args) throws Exception {

        NumberApi numberApi = lookup();
        System.out.println("The number is: " + numberApi.getNext());

    }

    public static NumberApi lookup() throws NamingException {

        final Hashtable<String, String> jndiProperties = new Hashtable();
        jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        final Context context = new InitialContext(jndiProperties);
        return (NumberApi) context.lookup("ejb:/eap6-ejb-remote/NumberApiBean!it.redhat.demo.NumberApi");

    }

}
