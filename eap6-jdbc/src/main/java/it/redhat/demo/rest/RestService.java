package it.redhat.demo.rest;

import it.redhat.demo.repo.JDBCRepo;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * Created by fabio on 27/10/16.
 */

@Path("")
@Stateless
public class RestService {

    @EJB
    private JDBCRepo repo;

    @GET
    public String ciao() {
        return "ciao";
    }

    @Path("query")
    @GET
    public Integer query() {
        return repo.executeQuery();
    }

}
