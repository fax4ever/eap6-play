package it.redhat.demo.cache;

import org.infinispan.configuration.cache.CacheMode;
import org.infinispan.configuration.cache.Configuration;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.configuration.global.GlobalConfiguration;
import org.infinispan.configuration.global.GlobalConfigurationBuilder;
import org.infinispan.manager.DefaultCacheManager;
import org.infinispan.manager.EmbeddedCacheManager;
import org.slf4j.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

/**
 * @author Fabio Massimo Ercoli
 *         fabio.ercoli@redhat.com
 *         on 19/07/16
 */

@Singleton
@Startup
public class CacheManagerProducer {

    @Inject
    private Logger log;

    private EmbeddedCacheManager cacheManager;

    @PostConstruct
    private void init() {

        GlobalConfiguration globalConfiguration = new GlobalConfigurationBuilder()
                .transport().defaultTransport().addProperty("configurationFile","jgroups.xml")
                .globalJmxStatistics().allowDuplicateDomains(true).enable()
                .build();

        Configuration defaultConfiguration = new ConfigurationBuilder()
                .clustering().cacheMode(CacheMode.DIST_ASYNC)
                .build();

        cacheManager = new DefaultCacheManager(globalConfiguration, defaultConfiguration);
        cacheManager.start();

    }

    @PreDestroy
    public void disposeCacheManager() {

        log.info("Stop embedded cache manager");
        cacheManager.stop();

    }

    @Produces
    public EmbeddedCacheManager getCacheManager() {
        return cacheManager;
    }

}
