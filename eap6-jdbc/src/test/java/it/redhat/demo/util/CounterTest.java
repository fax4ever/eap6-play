package it.redhat.demo.util;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Created by fabio on 28/10/16.
 */

@RunWith(JUnit4.class)
public class CounterTest {

    @Test
    public void verify() {

        Counter counter = new Counter(100);

        Assert.assertEquals(new Integer(0), counter.getCounter());
        Assert.assertTrue(counter.logThisTime());
        Assert.assertFalse(counter.isComplete());

        // +1
        counter.increment();

        Assert.assertEquals(new Integer(1), counter.getCounter());
        Assert.assertFalse(counter.logThisTime());
        Assert.assertFalse(counter.isComplete());

        // +9
        for (int i=0; i<9; i++) {
            counter.increment();
        }

        Assert.assertEquals(new Integer(10), counter.getCounter());
        Assert.assertTrue(counter.logThisTime());
        Assert.assertFalse(counter.isComplete());

        // +9
        for (int i=0; i<9; i++) {
            counter.increment();
        }

        Assert.assertEquals(new Integer(19), counter.getCounter());
        Assert.assertFalse(counter.logThisTime());
        Assert.assertFalse(counter.isComplete());

        // +1
        counter.increment();

        Assert.assertEquals(new Integer(20), counter.getCounter());
        Assert.assertTrue(counter.logThisTime());
        Assert.assertFalse(counter.isComplete());

        // +80
        for (int i=0; i<80; i++) {
            counter.increment();
        }

        Assert.assertEquals(new Integer(100), counter.getCounter());
        Assert.assertTrue(counter.logThisTime());
        Assert.assertTrue(counter.isComplete());

    }

}
