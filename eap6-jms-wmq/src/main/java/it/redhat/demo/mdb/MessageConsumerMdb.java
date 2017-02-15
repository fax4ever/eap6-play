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
 * Created by fabio.ercoli@redhat.com on 15/02/17.
 */
@MessageDriven(name = "MessageConsumerMdb", activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType",propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "useJNDI", propertyValue = "false"),
        @ActivationConfigProperty(propertyName = "hostName", propertyValue = "MQ.HOST.NAME"),
        @ActivationConfigProperty(propertyName = "port", propertyValue = "MQ.PORT"),
        @ActivationConfigProperty(propertyName = "channel", propertyValue = "MQ.CHANNEL.NAME"),
        @ActivationConfigProperty(propertyName = "queueManager", propertyValue = "MQ.QUEUE.MANAGER"),
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "MQ.QUEUE.NAME"),
        @ActivationConfigProperty(propertyName = "transportType", propertyValue = "MQ.CLIENT")
})
@ResourceAdapter(value = "wmq.jmsra.rar")
public class MessageConsumerMdb implements MessageListener {

    private static Logger log = LoggerFactory.getLogger(MessageConsumerMdb.class);

    @Override
    public void onMessage(Message message) {

        try {
            if (message instanceof TextMessage) {
                TextMessage msg = (TextMessage) message;
                String text = msg.getText();
                log.info("message received {}", text);
            } else {
                log.warn("message wrong type: {}", message.getClass().getName());
            }
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }

    }

}
