package it.redhat.demo;

import javax.ejb.Remote;

/**
 * @author Fabio Massimo Ercoli
 *         fabio.ercoli@redhat.com
 *         on 13/07/16
 */

@Remote
public interface NumberApi {

    int getNext();

}
