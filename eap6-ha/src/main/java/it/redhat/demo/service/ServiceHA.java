package it.redhat.demo.service;

import org.jboss.ejb3.annotation.Clustered;

import javax.ejb.Stateless;

/**
 * Created by fabio on 08/11/16.
 */

@Stateless
@Clustered
public class ServiceHA {

    public String ciao() {
        return "ciao";
    }

}
