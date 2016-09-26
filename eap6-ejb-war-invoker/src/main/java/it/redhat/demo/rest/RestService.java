package it.redhat.demo.rest;

import it.redhat.demo.gateway.EjbGateway;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * @author Fabio Massimo Ercoli
 *         fabio.ercoli@redhat.com
 *         on 09/06/16
 */

@Path("")
public class RestService {

    @GET
    public String letsGo() {

        EjbGateway gateway = new EjbGateway();
        return "let's go --> " + gateway.getNext();

    }

}
