package it.redhat.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import java.util.Hashtable;

/**
 * @author Fabio Massimo Ercoli
 *         fabio.ercoli@redhat.com
 *         on 30/05/16
 */
@RunWith(JUnit4.class)
public class SimpleCallIT {

    @Test
    public void go_admin() {

        Hashtable env = new Hashtable();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, "ldap://localhost:3890");
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        env.put(Context.SECURITY_PRINCIPAL, "cn=admin,dc=puppetlabs,dc=test");
        env.put(Context.SECURITY_CREDENTIALS, "test");

        try {
            new InitialDirContext(env);
        } catch (NamingException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void go_user() {

        Hashtable env = new Hashtable();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, "ldap://localhost:3890");
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        env.put(Context.SECURITY_PRINCIPAL, "uid=test,ou=people,dc=puppetlabs,dc=test");
        env.put(Context.SECURITY_CREDENTIALS, "pass");

        try {
            new InitialDirContext(env);
        } catch (NamingException e) {
            e.printStackTrace();
        }

    }

}
