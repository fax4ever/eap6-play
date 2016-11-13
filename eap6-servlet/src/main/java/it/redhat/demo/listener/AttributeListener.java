package it.redhat.demo.listener;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.annotation.WebListener;
import java.util.logging.Logger;

/**
 * Created by fabio on 13/11/16.
 */

@WebListener
public class AttributeListener implements ServletRequestAttributeListener {

    Logger log = Logger.getLogger(AttributeListener.class.getName());

    @Override
    public void attributeAdded(ServletRequestAttributeEvent event) {
        log.info("add :: " + event.getName() + " :: " + event.getValue());
    }

    @Override
    public void attributeRemoved(ServletRequestAttributeEvent event) {
        log.info("remove :: " + event.getName() + " :: " + event.getValue());
    }

    @Override
    public void attributeReplaced(ServletRequestAttributeEvent event) {
        log.info("replace :: " + event.getName() + " :: " + event.getValue());
    }

}
