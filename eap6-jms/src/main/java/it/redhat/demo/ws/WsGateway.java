package it.redhat.demo.ws;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

/**
 * Created by fabio on 08/11/16.
 */
@ServerEndpoint("/play-ws")
public class WsGateway {

    @OnMessage
    public String sayHello(String name) {
        System.out.println("Say hello to '" + name + "'");
        return ("Hello" + name);
    }

    @OnOpen
    public void helloOnOpen(Session session) {
        System.out.println("WebSocket opened: " + session.getId());
    }

    @OnClose
    public void helloOnClose(CloseReason reason) {
        System.out.println("Closing a WebSocket due to " + reason.getReasonPhrase());
    }

}
