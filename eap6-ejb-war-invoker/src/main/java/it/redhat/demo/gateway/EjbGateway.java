package it.redhat.demo.gateway;

import it.redhat.demo.NumberApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.Context;
import javax.naming.NamingException;
import java.util.Hashtable;

/**
 * Created by fabio on 26/09/16.
 */
public class EjbGateway {

    Logger log = LoggerFactory.getLogger(EjbGateway.class);

    public Integer getNext() {

        final Hashtable props = new Hashtable();
        props.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");

        try {

            final Context context = new javax.naming.InitialContext(props);
            NumberApi api = (NumberApi) context.lookup("ejb:/eap6-ejb-remote/NumberApiBean!it.redhat.demo.NumberApi");
            return api.getNext();

        } catch (NamingException e) {

            log.error(e.getMessage(), e);
            return null;

        }

    }

}
