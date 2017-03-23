package it.redhat.demo.mapper;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.JAXBException;

/**
 * Created by fabio.ercoli@redhat.com on 23/03/17.
 */
@Provider
public class JAXBExceptionMapper implements ExceptionMapper<JAXBException> {

    private static final Logger log = LoggerFactory.getLogger(JAXBExceptionMapper.class);

    @Override
    public Response toResponse(JAXBException e) {

        log.error(e.getMessage(), e);

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new ExceptionError(e.toString())).build();

    }

}
