package it.redhat.demo.ws;

import it.redhat.demo.jms.PlaySender;

import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * Created by fabio on 08/11/16.
 */
@ServerEndpoint("/play-ws")
@Singleton
public class WsGateway {

    @Inject
    private Logger log;

    @Inject
    private PlaySender sender;

    @Inject
    private SessionHolder sessionHolder;

    @OnMessage
    public void sayHello(String name) {
        log.info("Received message from websocket: " + name);
        sender.send("Hello " + name);
    }

    @OnOpen
    public void helloOnOpen(Session session) {
        log.info("WebSocket opened: " + session.getId());
        sessionHolder.put(session);
    }

    @OnClose
    public void helloOnClose(Session session, CloseReason reason) {
        log.info("Closing a WebSocket due to " + reason.getReasonPhrase());
        sessionHolder.remove(session);
    }

    public void send(String text) {

        log.info("Sending message to websocket: " + text);

        for (Session session : sessionHolder.getSessions()) {

            if (session.isOpen()) {
                try {
                    session.getBasicRemote().sendText(text);
                } catch (IOException ex) {
                    log.info(ex.getMessage());
                }
            }

        }
    }

}
