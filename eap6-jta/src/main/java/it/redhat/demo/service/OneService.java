package it.redhat.demo.service;

import org.slf4j.Logger;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import java.util.Random;

/**
 * Created by fabio on 13/11/16.
 */

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class OneService {

    @Inject
    private UserTransaction transaction;

    @Inject
    private Logger log;

    public Integer go() {

        log.info("{}", transaction);
        return new Random().nextInt();

    }

    public void rollbackForSure() {

        try {

            transaction.begin();
            transaction.setRollbackOnly();

        } catch (SystemException | NotSupportedException e) {
            log.error(e.getMessage(), e);
        }

    }

}
