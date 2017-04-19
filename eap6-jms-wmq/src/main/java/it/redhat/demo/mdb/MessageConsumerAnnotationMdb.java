package it.redhat.demo.mdb;

import org.jboss.ejb3.annotation.ResourceAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Created by fabio.ercoli@redhat.com on 19/04/17.
 */

@MessageDriven(name = "MessageConsumerAnnotationMdb", activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
    @ActivationConfigProperty(propertyName = "useJNDI", propertyValue = "false"),
    @ActivationConfigProperty(propertyName = "hostName", propertyValue = "mpebtx01.mycustomer.it"),
    @ActivationConfigProperty(propertyName = "port", propertyValue = "11421"),
    @ActivationConfigProperty(propertyName = "channel", propertyValue = "QS1XXLZ1.SC.WAS"),
    @ActivationConfigProperty(propertyName = "queueManager", propertyValue = "QS1XXLZ1"),
    @ActivationConfigProperty(propertyName = "destination", propertyValue = "BPMS.LAB.RESPONSE"),
    @ActivationConfigProperty(propertyName = "transportType", propertyValue = "CLIENT") })
@ResourceAdapter(value = "wmq.jmsra.rar")
public class MessageConsumerAnnotationMdb implements MessageListener {

    private static Logger log = LoggerFactory.getLogger(MessageConsumerAnnotationMdb.class);

    @Override
    public void onMessage(Message message) {

        try {
            if (message instanceof TextMessage) {
                TextMessage msg = (TextMessage) message;
                String text = msg.getText();
                log.info("message received {}", msg);
            } else {
                log.warn("message wrong type: {}", message.getClass().getName());
            }
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }

    }
}
