package it.redhat.demo.integration;

import it.redhat.demo.service.OneService;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

/**
 * Created by fabio on 13/11/16.
 */

@RunWith(Arquillian.class)
public class OneServiceIT {

    static Logger log = LoggerFactory.getLogger(OneServiceIT.class);

    @Deployment
    public static JavaArchive deployment() {

        return ShrinkWrap.create(JavaArchive.class)
                .addPackages(true, "it.redhat.demo")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");

    }

    @Inject
    public OneService testObject;

    @Test
    public void test() {

        Integer go = testObject.go();

        Assert.assertNotNull(go);

        log.info("random number :: {}", go);

    }


}
