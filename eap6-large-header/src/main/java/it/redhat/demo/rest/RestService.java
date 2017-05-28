package it.redhat.demo.rest;

import org.slf4j.Logger;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;

/**
 * @author Fabio Massimo Ercoli
 *         fabio.ercoli@redhat.com
 *         on 18/07/16
 */

@Path("")
public class RestService {

    @Inject
    private Logger log;

    @GET
    public Integer header(@Context HttpHeaders headers) {

        final int[] response = {0};

        MultivaluedMap<String, String> requestHeaders = headers.getRequestHeaders();
        requestHeaders.forEach((key, value) -> {
            log.info("Header name {} size {} value {}", key, value.get(0).length(), value);
            value.forEach(val -> {
                response[0] += val.length();
            });
        });

        return response[0];
    }

}
