package it.redhat.demo.mdb;

import org.jboss.ejb3.annotation.ResourceAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

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
