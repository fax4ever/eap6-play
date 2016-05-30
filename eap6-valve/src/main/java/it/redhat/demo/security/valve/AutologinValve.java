package it.redhat.demo.security.valve;

import org.apache.catalina.authenticator.BasicAuthenticator;
import org.apache.catalina.authenticator.Constants;
import org.apache.catalina.connector.Request;
import org.apache.catalina.deploy.LoginConfig;
import org.apache.catalina.util.Base64;
import org.apache.tomcat.util.buf.ByteChunk;
import org.apache.tomcat.util.buf.CharChunk;
import org.apache.tomcat.util.buf.MessageBytes;
import org.jboss.web.CatalinaLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;

/**
 * @author Fabio Massimo Ercoli
 *         fabio.ercoli@redhat.com
 *         on 27/05/16
 */
public class AutologinValve extends BasicAuthenticator {

    private Logger log = LoggerFactory.getLogger(AutologinValve.class);

    @Override
    public boolean authenticate(Request request, HttpServletResponse response, LoginConfig config) throws IOException {

        // Have we already authenticated someone?
        Principal principal = request.getUserPrincipal();
        String ssoId = (String) request.getNote(Constants.REQ_SSOID_NOTE);
        if (principal != null) {
            if (CatalinaLogger.AUTH_LOGGER.isDebugEnabled())
                CatalinaLogger.AUTH_LOGGER.debug("Already authenticated '" + principal.getName() + "'");
            // Associate the session with any existing SSO session
            if (ssoId != null)
                associate(ssoId, request.getSessionInternal(true));
            return (true);
        }

        // Is there an SSO session against which we can try to reauthenticate?
        if (ssoId != null) {
            if (CatalinaLogger.AUTH_LOGGER.isDebugEnabled())
                CatalinaLogger.AUTH_LOGGER.debug("SSO Id " + ssoId + " set; attempting " +
                        "reauthentication");
            /* Try to reauthenticate using data cached by SSO.  If this fails,
               either the original SSO logon was of DIGEST or SSL (which
               we can't reauthenticate ourselves because there is no
               cached username and password), or the realm denied
               the user's reauthentication for some reason.
               In either case we have to prompt the user for a logon */
            if (reauthenticateFromSSO(ssoId, request))
                return true;
        }

        // Validate any credentials already included with this request
        String username = null;
        String password = null;

        // basic auth
        if (basicAuth(request, response, username, password)) return (true);

        // Validate any credentials already included with this request
        if (headerAuth(request, response)) return (true);

        // Send an "unauthorized" response and an appropriate challenge
        MessageBytes authenticate = request.getResponse().getCoyoteResponse().getMimeHeaders().addValue(AUTHENTICATE_BYTES, 0, AUTHENTICATE_BYTES.length);
        CharChunk authenticateCC = authenticate.getCharChunk();
        authenticateCC.append("Basic realm=\"");
        if (config.getRealmName() == null) {
            authenticateCC.append("Realm");
        } else {
            authenticateCC.append(config.getRealmName());
        }
        authenticateCC.append('\"');
        authenticate.toChars();
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);

        return false;

    }

    private boolean headerAuth(Request request, HttpServletResponse response) {
        String username;
        String password;
        Principal principal;
        username = request.getHeader("username");
        password = request.getHeader("password");

        if (username != null && password != null) {
            principal = context.getRealm().authenticate(username, password);
            if (principal != null) {
                register(request, response, principal, HttpServletRequest.BASIC_AUTH, username, password);
                return true;
            }
        }
        return false;
    }

    private boolean basicAuth(Request request, HttpServletResponse response, String username, String password) {
        Principal principal;
        MessageBytes authorization = request.getCoyoteRequest().getMimeHeaders().getValue("authorization");

        if (authorization != null) {
            authorization.toBytes();
            ByteChunk authorizationBC = authorization.getByteChunk();
            if (authorizationBC.startsWithIgnoreCase("basic ", 0)) {
                authorizationBC.setOffset(authorizationBC.getOffset() + 6);

                CharChunk authorizationCC = authorization.getCharChunk();
                Base64.decode(authorizationBC, authorizationCC);

                // Get username and password
                int colon = authorizationCC.indexOf(':');
                if (colon < 0) {
                    username = authorizationCC.toString();
                } else {
                    char[] buf = authorizationCC.getBuffer();
                    username = new String(buf, 0, colon);
                    password = new String(buf, colon + 1,
                            authorizationCC.getEnd() - colon - 1);
                }

                authorizationBC.setOffset(authorizationBC.getOffset() - 6);
            }

            principal = context.getRealm().authenticate(username, password);
            if (principal != null) {
                register(request, response, principal, HttpServletRequest.BASIC_AUTH,
                        username, password);
                return true;
            }
        }
        return false;
    }

}
