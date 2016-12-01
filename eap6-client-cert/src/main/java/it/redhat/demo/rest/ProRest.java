package it.redhat.demo.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * Created by fabio on 25/11/16.
 */

@Path("pro")
public class ProRest {

    @GET
    public String pro() {
        return "pro";
    }

}
