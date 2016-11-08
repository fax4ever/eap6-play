package it.redhat.demo.jms;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.*;
import java.util.logging.Logger;

/**
 * Created by fabio on 08/11/16.
 */

@Stateless
public class PlaySender {

    @Inject
    private Logger log;

    @Resource(mappedName = "java:/ConnectionFactory")
    private ConnectionFactory connectionFactory;

    @Resource(mappedName = "java:/queue/PlayQueue")
    private Queue queue;

    public void send(String text) {

        log.info("Sending message to queue: " + text);

        Connection connection = null;

        try {

            connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer messageProducer = session.createProducer(queue);
            connection.start();
            TextMessage message = session.createTextMessage(text);
            messageProducer.send(message);

        } catch (JMSException e) {

            log.info(e.getMessage());

        } finally {

            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException e) {
                    log.info(e.getMessage());
                }
            }

        }

    }

}
