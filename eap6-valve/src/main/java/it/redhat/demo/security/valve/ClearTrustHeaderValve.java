package it.redhat.demo.security.valve;

import org.apache.catalina.Realm;
import org.apache.catalina.Session;
import org.apache.catalina.authenticator.FormAuthenticator;
import org.apache.catalina.connector.Request;
import org.apache.catalina.deploy.LoginConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;

import static org.apache.catalina.authenticator.Constants.FORM_PRINCIPAL_NOTE;
import static org.apache.catalina.authenticator.Constants.SESS_PASSWORD_NOTE;
import static org.apache.catalina.authenticator.Constants.SESS_USERNAME_NOTE;
import static org.apache.catalina.realm.Constants.*;

public class ClearTrustHeaderValve extends FormAuthenticator {

    private static final Logger log = LoggerFactory.getLogger(ClearTrustHeaderValve.class);
    private static final String FORM_METHOD = "FORM";
    public static final String CLEAR_TRUST_HEADER_TOKEN = "CLEAR_TRUST_HEADER_TOKEN";

    @Override
    public boolean authenticate(final Request request, final HttpServletResponse response, final LoginConfig config) throws IOException {
        boolean DEBUG = log.isDebugEnabled();
        log.trace("Authenticating user");
        Principal principal = request.getUserPrincipal();
        if (principal != null) {
            if (log.isTraceEnabled())
                log.trace("Already authenticated '" + principal.getName() + "'");
            return true;
        }

        String contextPath = request.getContextPath();
        String requestURI = request.getDecodedRequestURI();
        boolean loginAction = requestURI.startsWith(contextPath) && requestURI.endsWith(FORM_ACTION);

        // FORM LOGIN
        if (loginAction) {
            log.trace("FORM Login Action");
            log.trace("FORM Original Request " + requestURI);
            Realm realm = context.getRealm();
            String username = request.getParameter("user");
            String password = request.getParameter("pass");
            principal = realm.authenticate(username, password);
            if (principal == null) {
                log.trace("Principal NULL");
                RequestDispatcher disp = context.getServletContext().getRequestDispatcher(config.getErrorPage());
                try {
                    disp.forward(request.getRequest(), response);
                } catch (ServletException e) {
                    IOException ex = new IOException("Unable to forward to error page.");
                    ex.initCause(e);
                    throw ex;
                }
                return false;
            }
            Session session = request.getSessionInternal();
            requestURI = savedRequestURL(session);
            session.setNote(FORM_PRINCIPAL_NOTE, principal);
            session.setNote(SESS_USERNAME_NOTE, username);
            session.setNote(SESS_PASSWORD_NOTE, password);
            log.trace("User Name [" + username + "]");
            register(request, response, principal, FORM_METHOD, username, password);
            log.trace("Request URI [" + response.encodeRedirectURL(requestURI) + "]");
            response.sendRedirect(response.encodeRedirectURL(requestURI));
            return false;
        }

        String tokenFound = request.getHeader(CLEAR_TRUST_HEADER_TOKEN);
        log.trace("Authenticating user [" + tokenFound + "]");
        String authenticationMethod = "BASIC";
        try {
            Realm realm = context.getRealm();
            log.trace("Info " + realm.getInfo());
            principal = realm.authenticate(tokenFound, (String) null);
        } catch (Exception e) {
            e.initCause(e);
            return false;
        }
        boolean result;
        if (principal != null) {
            register(request, response, principal, authenticationMethod, tokenFound, null);
            result = true;
        } else {
            log.trace("Principal is: " + principal);
            result = false;
        }
        return result;
    }

}
