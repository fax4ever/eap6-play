package it.redhat.demo.impl;

import it.redhat.demo.NumberApi;
import org.jboss.ejb3.annotation.Clustered;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.Random;

/**
 * @author Fabio Massimo Ercoli
 *         fabio.ercoli@redhat.com
 *         on 13/07/16
 */
@Stateless
@Remote(NumberApi.class)
@Clustered
public class NumberApiBean implements NumberApi {

    Logger log = LoggerFactory.getLogger(NumberApiBean.class);

    @Override
    public int getNext() {

        int value = new Random(1000).nextInt();

        log.info("sending value :: {}", value);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
        }

        return value;
    }

}
