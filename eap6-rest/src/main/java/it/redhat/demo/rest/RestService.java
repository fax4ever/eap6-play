package it.redhat.demo.rest;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.redhat.demo.model.Command;

/**
 * @author Fabio Massimo Ercoli
 *         fabio.ercoli@redhat.com
 *         on 18/07/16
 */

@Path("/")
public class RestService {

    Logger log = LoggerFactory.getLogger(RestService.class);

    @GET
    public String ciao() {
        log.info("ciao");
        return "ciao";
    }

    @POST
    public void start() {
        log.info("START");
    }

    @DELETE
    public void stop() {
        log.info("STOP");
    }

    @POST
    @Path("error")
    public void error() {
        throw new RuntimeException();
    }
    
    @POST
    @Path("command")
    public void command(Command command) {
    	log.info("receive command {}", command);
    }

}
