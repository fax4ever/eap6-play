package it.redhat.demo.rest;

import it.redhat.demo.repo.JDBCRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

/**
 * Created by fabio on 27/10/16.
 */

@Path("")
@Stateless
public class RestService {

    private static Logger log = LoggerFactory.getLogger(RestService.class);

    @EJB
    private JDBCRepo repo;

    @GET
    public String ciao() {
        return "ciao";
    }

    @Path("query")
    @GET
    public Integer query(@QueryParam("times") Integer times) {

        if (times == null) {
            times = 10;
        }

        for (int i=0; i<times; i++) {
            repo.executeQuery(i);
            log.info("sended {}", i);
        }

        return times;

    }

}
