package it.redhat.demo.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * @author Fabio Massimo Ercoli
 *         fabio.ercoli@redhat.com
 *         on 18/07/16
 */

@Path("")
public class RestService {

    Logger log = LoggerFactory.getLogger(RestService.class);

    @GET
    public String ciao() {
        log.info("ciao");
        return "ciao";
    }

}
