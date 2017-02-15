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

        try {

            connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer messageProducer = session.createProducer(queue);
            connection.start();
            TextMessage message = session.createTextMessage("{{ Message Body }}");
            messageProducer.send(message);
            log.info("message sent");

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

