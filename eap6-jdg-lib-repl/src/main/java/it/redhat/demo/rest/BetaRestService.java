package it.redhat.demo.rest;

import org.infinispan.manager.EmbeddedCacheManager;
import org.slf4j.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;

/**
 * @author Fabio Massimo Ercoli
 *         fabio.ercoli@redhat.com
 *         on 19/07/16
 */
@Path(BetaRestService.CACHE_NAME)
@Stateless
public class BetaRestService {

    public static final String CACHE_NAME = "beta";

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

        cacheManager.getCache(CACHE_NAME).put(key, value);

    }

    @Path("{key}")
    @GET
    public String get(@PathParam("key") String key) {

        return (String) cacheManager.getCache(CACHE_NAME).get(key);

    }

    @Path("{key}")
    @DELETE
    public void remove(@PathParam("key") String key) {

        cacheManager.getCache(CACHE_NAME).remove(key);

    }

}
