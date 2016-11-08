package it.redhat.demo.ws;

import javax.inject.Inject;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.logging.Logger;

/**
 * Created by fabio on 08/11/16.
 */
@ServerEndpoint("/play-ws")
public class WsGateway {

    @Inject
    private Logger log;

    @OnMessage
    public String sayHello(String name) {
        log.info("Say hello to '" + name + "'");
        return ("Hello " + name);
    }

    @OnOpen
    public void helloOnOpen(Session session) {
        log.info("WebSocket opened: " + session.getId());
    }

    @OnClose
    public void helloOnClose(CloseReason reason) {
        log.info("Closing a WebSocket due to " + reason.getReasonPhrase());
    }

}
