package it.redhat.demo.rest;

import org.infinispan.manager.EmbeddedCacheManager;
import org.slf4j.Logger;

import javax.inject.Inject;
import javax.ws.rs.*;

/**
 * @author Fabio Massimo Ercoli
 *         fabio.ercoli@redhat.com
 *         on 19/07/16
 */
@Path("cache")
public class RestService {

    @Inject
    private Logger log;

    @Inject
    private EmbeddedCacheManager cacheManager;

    @POST
    public void start() {
        log.info("START");
    }

    @DELETE
    public void stop() {
        log.info("STOP");
    }

    @Path("{key}")
    @PUT
    public void put(@PathParam("key") String key, String value) {

        cacheManager.getCache("alfa").put(key, value);

    }

    @Path("{key}")
    @GET
    public String get(@PathParam("key") String key) {

        return (String) cacheManager.getCache("alfa").get(key);

    }

    @Path("{key}")
    @DELETE
    public void remove(@PathParam("key") String key) {

        cacheManager.getCache("alfa").remove(key);

    }

}
