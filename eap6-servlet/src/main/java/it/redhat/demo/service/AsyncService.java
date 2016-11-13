package it.redhat.demo.service;

import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.servlet.AsyncContext;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by fabio on 13/11/16.
 */

@Stateless
public class AsyncService {

    private static Logger log = Logger.getLogger(AsyncService.class.getName());

    @Asynchronous
    public void readData(AsyncContext context) {

        try {

            log.info("another thread log this message");

            // long running simulation
            Thread.sleep(5000);

            PrintWriter writer = context.getResponse().getWriter();
            writer.print(new SimpleDateFormat("HH:mm:ss").format(new Date()));
            writer.close();

            context.complete();

        } catch (Exception e) {

            log.log(Level.SEVERE, e.getMessage(), e);

        }


    }

}
