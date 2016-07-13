package it.redhat.demo.impl;

import it.redhat.demo.NumberApi;

import javax.ejb.Remote;
import javax.ejb.Stateless;

/**
 * @author Fabio Massimo Ercoli
 *         fabio.ercoli@redhat.com
 *         on 13/07/16
 */
@Stateless
@Remote(NumberApi.class)
public class NumberApiBean implements NumberApi {

    @Override
    public int getNext() {
        return (int) (Math.random() * 100);
    }

}
