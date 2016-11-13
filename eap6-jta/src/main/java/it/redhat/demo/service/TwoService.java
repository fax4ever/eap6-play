package it.redhat.demo.service;

import it.redhat.demo.exception.MyException;

import javax.ejb.ApplicationException;
import javax.ejb.Stateless;

/**
 * Created by fabio on 13/11/16.
 */

@Stateless
@ApplicationException(rollback = true)
public class TwoService {

    public void rollbackOnApplicationException() throws MyException {

        throw new MyException("daje");

    }

    public void rollbackOnRuntime() {

        throw new RuntimeException("ok");

    }

}
