package it.redhat.demo.wmq;

import it.redhat.demo.qualifier.StartProcess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.jms.*;
import java.util.UUID;

/**
 * Created by fabio.ercoli@redhat.com on 19/04/17.
 */

@Singleton
@Startup
public class JmsDriver {

    private static final long TIME_OUT = 1000;
    private static final Logger LOG = LoggerFactory.getLogger(JmsDriver.class);

    @Resource(mappedName = "java:/MQConnectionFactory")
    private ConnectionFactory connectionFactory;

    @Resource(mappedName = "java:/mqRequest")
    private Queue requestQueue;

    @Resource(mappedName = "java:/mqResponse")
    private Queue responseQueue;

    @Inject
    @StartProcess
    private String startProcessPayload;

    @PostConstruct
    public void sendAndReceive() {

        // for request
        String corrId = UUID.randomUUID().toString();
        // for response
        String selector = "JMSCorrelationID = '" + corrId + "'";

        Connection connection = null;
        Session session = null;

        try {

            connection = connectionFactory.createConnection();
            session = connection.createSession(true, Session.CLIENT_ACKNOWLEDGE);
            MessageProducer messageProducer = session.createProducer(requestQueue);
            MessageConsumer messageConsumer = session.createConsumer(responseQueue);
            connection.start();

            TextMessage requestMessage = session.createTextMessage(startProcessPayload);

            requestMessage.setJMSCorrelationID(corrId);
            requestMessage.setIntProperty("serialization_format", 2);
            requestMessage.setIntProperty("kie_interaction_pattern", 1);
            requestMessage.setStringProperty("kie_class_type", "org.kie.server.api.commands.DescriptorCommand");
            requestMessage.setStringProperty("kie_target_capability", "BPM");
            requestMessage.setStringProperty("container_id", "main");

            LOG.info("sending message\n{}", requestMessage);
            messageProducer.send(requestMessage);

            TextMessage responseMessage = (TextMessage) messageConsumer.receive(TIME_OUT);
            LOG.info("received message\n{}", responseMessage);

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

}
