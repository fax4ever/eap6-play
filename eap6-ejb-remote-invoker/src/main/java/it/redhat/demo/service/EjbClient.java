package it.redhat.demo.service;

import it.redhat.demo.NumberApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    private Logger log = LoggerFactory.getLogger(EjbClient.class);

    public Integer go() {

        final Hashtable<String, String> jndiProperties = new Hashtable<String, String>();
        jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");

        try {

            final Context context = new InitialContext(jndiProperties);
            NumberApi numberApi = (NumberApi) context.lookup("java:global/eap6-ejb-remote/NumberApiBean!it.redhat.demo.NumberApi");
            return numberApi.getNext();

        } catch (NamingException e) {

            log.error(e.getMessage(), e);

        }

        return null;
    }

}
