package it.redhat.demo.rest;

import org.slf4j.Logger;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * @author Fabio Massimo Ercoli
 *         fabio.ercoli@redhat.com
 *         on 18/07/16
 */

@Path("")
public class RestService {

    @Inject
    private Logger log;

    @GET
    public String ciao() {
        log.info("ciao");
        return "ciao";
    }

}
