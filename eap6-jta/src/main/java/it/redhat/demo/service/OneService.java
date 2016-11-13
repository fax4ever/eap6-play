package it.redhat.demo.service;

import javax.ejb.Stateless;
import java.util.Random;

/**
 * Created by fabio on 13/11/16.
 */

@Stateless
public class OneService {

    public Integer go() {
        return new Random().nextInt();
    }

}
