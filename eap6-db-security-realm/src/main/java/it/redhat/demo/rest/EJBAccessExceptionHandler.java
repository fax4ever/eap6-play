package it.redhat.demo.rest;

import javax.ejb.EJBAccessException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * @author Fabio Massimo Ercoli
 *         fabio.ercoli@redhat.com
 *         on 27/05/16
 */
@Provider
public class EJBAccessExceptionHandler implements ExceptionMapper<EJBAccessException> {

    @Override
    public Response toResponse(EJBAccessException e) {
        return Response.status(403).entity("403 :: Not Allowed! " + e.getMessage()).build();
    }

}
