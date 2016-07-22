package it.redhat.demo.rest.impl;

import it.redhat.demo.rest.BaseRestService;
import org.infinispan.manager.EmbeddedCacheManager;
import org.slf4j.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Path;

/**
 * @author Fabio Massimo Ercoli
 *         fabio.ercoli@redhat.com
 *         on 19/07/16
 */
@Stateless
@Path(FanRestService.CACHE_NAME)
public class FanRestService extends BaseRestService {

    public static final String CACHE_NAME = "fan";

    @Inject
    private Logger log;

    @Inject
    private EmbeddedCacheManager cacheManager;

    @PostConstruct
    private void init() {

        cache = cacheManager.getCache(CACHE_NAME);

    }

}
