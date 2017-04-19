package it.redhat.demo.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.jms.*;

/**
 * Created by fabio.ercoli@redhat.com on 15/02/17.
 */

@Singleton
@Startup
public class MessageProducerJms {

    private static Logger log = LoggerFactory.getLogger(MessageProducerJms.class);

    @Resource(mappedName = "java:/MQConnectionFactory")
    private ConnectionFactory connectionFactory;

    @Resource(mappedName = "java:/PlayQueue")
    private Queue queue;

    @PostConstruct
    public void start() {

        sendMessage();

    }

    private void sendMessage() {
        Connection connection = null;
        Session session = null;

        try {

            connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer messageProducer = session.createProducer(queue);
            connection.start();
            TextMessage message = session.createTextMessage("{{ Message Body }}");
            messageProducer.send(message);
            log.info("message sent {}", message);

        } catch (JMSException e) {

            log.error(e.getMessage(), e);

        } finally {

            if (connection != null) {
                try {
                    connection.close();
                    if (session != null) {
                        session.close();
                    }
                } catch (JMSException ex) {
                    log.warn("Unable to close connection or session!", ex);
                }
            }

        }
    }


}

