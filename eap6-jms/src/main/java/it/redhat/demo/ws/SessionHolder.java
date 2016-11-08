package it.redhat.demo.ws;

import javax.annotation.PreDestroy;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.websocket.Session;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

/**
 * Created by fabio on 08/11/16.
 */

@Singleton
public class SessionHolder {

    @Inject
    private Logger log;

    private Set<Session> sessions = new HashSet<>();

    public void put(Session session) {
        sessions.add(session);
    }

    public Set<Session> getSessions() {
        return sessions;
    }

    public void remove(Session session) {
        sessions.remove(session);
    }

    @PreDestroy
    public void onExit() {

        for (Session session : sessions) {

            try {
                session.close();
            } catch (IOException ex) {
                log.info(ex.getMessage());
            }

        }

    }

}
