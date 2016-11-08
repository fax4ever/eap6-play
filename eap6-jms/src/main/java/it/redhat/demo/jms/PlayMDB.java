package it.redhat.demo.jms;

import it.redhat.demo.ws.WsGateway;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.util.logging.Logger;

/**
 * Created by fabio on 08/11/16.
 */
@MessageDriven(name = "PlayMdb", activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue ="javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/PlayQueue"),
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")
})
public class PlayMDB implements MessageListener {

    @Inject
    private Logger log;

    @Inject
    private WsGateway gateway;

    @Override
    public void onMessage(Message message) {

        TextMessage msg = null;
        try {
            if (message instanceof TextMessage) {
                msg = (TextMessage) message;
                String text = msg.getText();
                log.info("Received message from queue: " + text);

                gateway.send(text);
            } else {
                log.warning("Message of wrong type: " + message.getClass().getName());
            }
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }

    }

}
