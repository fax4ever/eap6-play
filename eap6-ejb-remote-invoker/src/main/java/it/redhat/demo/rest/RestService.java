package it.redhat.demo.rest;

import it.redhat.demo.service.EjbClient;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author Fabio Massimo Ercoli
 *         fabio.ercoli@redhat.com
 *         on 09/06/16
 */
@Path("")
@Produces(MediaType.APPLICATION_JSON)
public class RestService {

    @Inject
    private EjbClient ejbClient;

    @GET
    public String letsGo() {
        return "start " + ejbClient.go();
    }

}
