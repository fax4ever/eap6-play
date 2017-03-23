package it.redhat.demo.rest;

import it.redhat.demo.model.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

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

    @GET
    @Path("task")
    @Produces({ "application/xml" })
    public Task task() {
        Task task = new Task();
        task.setId(1l);
        task.setTitle("My Task");

        return task;
    }

}
