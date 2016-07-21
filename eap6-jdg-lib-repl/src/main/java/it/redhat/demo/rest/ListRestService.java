package it.redhat.demo.rest;

import org.infinispan.Cache;
import org.infinispan.commons.util.CloseableIteratorCollection;
import org.infinispan.manager.EmbeddedCacheManager;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Fabio Massimo Ercoli
 *         fabio.ercoli@redhat.com
 *         on 21/07/16
 */
@Path("list")
@Stateless
public class ListRestService {

    @Inject
    private EmbeddedCacheManager cacheManager;

    private Cache<String, String> alfa;
    private Cache<String, String> beta;

    @PostConstruct
    private void loadCache() {

        alfa = cacheManager.getCache(AlfaRestService.CACHE_NAME);
        beta = cacheManager.getCache(BetaRestService.CACHE_NAME);

    }

    @GET
    @Path(AlfaRestService.CACHE_NAME + "/{max}")
    public List<String> getAlfas(@PathParam("max") Integer max) {

        ArrayList<String> result = new ArrayList<>();

        for (int i=0; i<max; i++) {
            String value = alfa.get("akey" + i);
            if (value != null) {
                result.add(value);
            }
        }

        return result;

    }

    @GET
    @Path(BetaRestService.CACHE_NAME+ "/{max}")
    public List<String> getBetas(@PathParam("max") Integer max) {

        ArrayList<String> result = new ArrayList<>();

        for (int i=0; i<max; i++) {
            String value = alfa.get("bkey" + i);
            if (value != null) {
                result.add(value);
            }
        }

        return result;

    }

}
