package it.redhat.demo.rest;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Created by fabio on 26/09/16.
 */

@Provider
public class IllegalStateExceptionHandler implements ExceptionMapper<IllegalStateException> {

    @Override
    public Response toResponse(IllegalStateException e) {
        return Response.status(500).entity(e.getMessage()).build();
    }

}
