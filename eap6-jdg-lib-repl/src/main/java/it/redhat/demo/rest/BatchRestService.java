package it.redhat.demo.rest;

import org.infinispan.Cache;
import org.infinispan.manager.EmbeddedCacheManager;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/**
 * @author Fabio Massimo Ercoli
 *         fabio.ercoli@redhat.com
 *         on 21/07/16
 */

@Stateless
@Path("batch")
public class BatchRestService {

    @Inject
    private EmbeddedCacheManager cacheManager;

    private Cache<String, String> alfa;
    private Cache<String, String> beta;

    @PostConstruct
    private void loadCache() {

        alfa = cacheManager.getCache(AlfaRestService.CACHE_NAME);
        beta = cacheManager.getCache(BetaRestService.CACHE_NAME);

    }

    @POST
    @Path("{max}")
    public void ok(@PathParam("max") Integer max) {

        alfa.startBatch();
        beta.startBatch();

        alfa.clear();
        beta.clear();
        for (int i=0; i<max; i++) {
            alfa.put("akey" + i, "avalue" + i);
            beta.put("bkey" + i, "bvalue" + i);
        }

        alfa.endBatch(true);
        beta.endBatch(true);

    }

    @DELETE
    @Path("{max}")
    public void ko(@PathParam("max") Integer max) {

        alfa.startBatch();
        beta.startBatch();

        alfa.clear();
        beta.clear();
        for (int i=0; i<max; i++) {
            alfa.put("akey" + i, "avalue" + i + "WRONG");
            beta.put("bkey" + i, "bvalue" + i + "WRONG");
        }

        throw new RuntimeException();

    }

}
