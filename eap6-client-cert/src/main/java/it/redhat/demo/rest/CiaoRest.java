package it.redhat.demo.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * Created by fabio on 25/11/16.
 */
@Path("ciao")
public class CiaoRest {

    @GET
    public String ciao() {
        return "ciao";
    }

}
