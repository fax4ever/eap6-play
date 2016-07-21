package it.redhat.demo.cache;

import it.redhat.demo.rest.AlfaRestService;
import it.redhat.demo.rest.BetaRestService;
import org.infinispan.configuration.cache.*;
import org.infinispan.configuration.global.GlobalConfiguration;
import org.infinispan.configuration.global.GlobalConfigurationBuilder;
import org.infinispan.manager.DefaultCacheManager;
import org.infinispan.manager.EmbeddedCacheManager;
import org.infinispan.transaction.LockingMode;
import org.infinispan.transaction.TransactionMode;
import org.infinispan.transaction.lookup.GenericTransactionManagerLookup;
import org.infinispan.util.concurrent.IsolationLevel;
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
                .transport()
                    .defaultTransport()
                    .clusterName("eap6-jdg-lib-repl")
                    .distributedSyncTimeout(600000l)
                    .addProperty("configurationFile","jgroups.xml")
                .globalJmxStatistics()
                    .allowDuplicateDomains(true)
                    .enable()
                .build();

        InvocationBatchingConfigurationBuilder defaultBuilder = new ConfigurationBuilder()
                .clustering()
                    .cacheMode(CacheMode.REPL_ASYNC)
                    .stateTransfer()
                        .timeout(30000000)
                        .fetchInMemoryState(true)
                        .chunkSize(1048576)
                        .awaitInitialTransfer(true)
                .transaction()
                    .transactionMode(TransactionMode.TRANSACTIONAL)
                    .lockingMode(LockingMode.OPTIMISTIC)
                    .transactionManagerLookup(new GenericTransactionManagerLookup())
                    .syncCommitPhase(true)
                    .useSynchronization(false)
                    .cacheStopTimeout(10000)
                .locking()
                    .isolationLevel(IsolationLevel.READ_COMMITTED)
                    .concurrencyLevel(1000)
                    .useLockStriping(false)
                    .lockAcquisitionTimeout(600000l)
                .invocationBatching()
                    .enable(true);

        Configuration alfaConfiguration = defaultBuilder.build();
        Configuration betaConfiguration = defaultBuilder.build();

        cacheManager = new DefaultCacheManager(globalConfiguration, defaultBuilder.build());
        cacheManager.defineConfiguration(AlfaRestService.CACHE_NAME, alfaConfiguration);
        cacheManager.defineConfiguration(BetaRestService.CACHE_NAME, betaConfiguration);

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
