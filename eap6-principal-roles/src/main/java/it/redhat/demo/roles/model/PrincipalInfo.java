package it.redhat.demo.roles.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by fabio.ercoli@redhat.com on 08/06/2017.
 */
public class PrincipalInfo {

    private boolean principal = false;

    private String username;

    private Set<String> roles;

    public PrincipalInfo() {
    }

    public PrincipalInfo(String username) {
        this.principal = true;
        this.username = username;
        this.roles = new HashSet<>();
    }

    public boolean isPrincipal() {
        return principal;
    }

    public String getUsername() {
        return username;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void addRole(String role) {
        roles.add(role);
    }

}
