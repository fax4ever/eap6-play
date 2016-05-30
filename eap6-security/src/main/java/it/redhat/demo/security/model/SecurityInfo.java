package it.redhat.demo.security.model;

import java.io.Serializable;

/**
 * @author Fabio Massimo Ercoli
 *         fabio.ercoli@redhat.com
 *         on 27/05/16
 */
public class SecurityInfo implements Serializable {

    private String username = "NO_USER";

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
