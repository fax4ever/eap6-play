package it.redhat.demo.eap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.*;
import java.util.Enumeration;

/**
 * Created by fabio.ercoli@redhat.com on 19/04/17.
 */

@MessageDriven(name = "JmsStub", activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue ="javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/KIE.SERVER.REQUEST"),
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")
})
public class JmsStub implements MessageListener {

    private static final Logger LOG = LoggerFactory.getLogger(JmsStub.class);

    @Resource(mappedName = "java:/ConnectionFactory")
    private ConnectionFactory connectionFactory;

    @Resource(mappedName = "java:/queue/KIE.SERVER.RESPONSE")
    private Queue responseQueue;

    @Override
    public void onMessage(Message message) {

        TextMessage requestMessage = (TextMessage) message;
        logMessage("received message", requestMessage);

        String corrId;
        String text;

        try {
            corrId = requestMessage.getJMSCorrelationID();
            text = requestMessage.getText();
        } catch (JMSException ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        }

        Connection connection = null;
        Session session = null;

        try {
            connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer messageProducer = session.createProducer(responseQueue);
            TextMessage responseMessage = session.createTextMessage("Response to --> " + text);
            responseMessage.setJMSCorrelationID(corrId);

            logMessage("sending message", responseMessage);
            messageProducer.send(responseMessage);

        } catch (JMSException ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        } finally {

            if (connection != null) {
                try {
                    connection.close();
                    if (session != null) {
                        session.close();
                    }
                } catch (JMSException ex) {
                    LOG.warn("Unable to close connection or session!", ex);
                }
            }

        }

    }

    private void logMessage(String action, TextMessage textMessage) {

        try {
            LOG.info("{}\n{}",action, textMessage.getText());
            Enumeration srcProperties = textMessage.getPropertyNames();
            while (srcProperties.hasMoreElements()) {
                String propertyName = (String)srcProperties.nextElement ();
                Object propertyValue = textMessage.getObjectProperty(propertyName);
                LOG.info("property name {} - value {}", propertyName, propertyValue);
            }
        } catch (JMSException e) {
            LOG.warn("error on log message", e);
        }


    }

}
