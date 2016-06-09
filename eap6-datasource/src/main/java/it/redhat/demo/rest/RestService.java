package it.redhat.demo.rest;

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
        return "let's go!";
    }

}
