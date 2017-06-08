package it.redhat.demo.roles.rest;

import it.redhat.demo.roles.model.PrincipalInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.security.Principal;

/**
 * @author Fabio Massimo Ercoli
 *         fabio.ercoli@redhat.com
 *         on 27/05/16
 */
@Path("")
@Stateless
public class RestService {

    private Logger LOG = LoggerFactory.getLogger(RestService.class);

    private final static String[] ALL_ROLES = {"admin", "analyst", "manager", "kie-server", "VAGE-GI", "VASE-GI", "VASS-GI", "VAUT-GI", "VCM1-GI", "VCM2-GI", "VVAC-GI", "VVCC-GI", "VVSC-GI"};

    @Context
    private HttpServletRequest servletRequest;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public PrincipalInfo getSecurityContext() {

        Principal principal = servletRequest.getUserPrincipal();

        if (principal == null) {
            return new PrincipalInfo();
        }

        LOG.info("{}", principal.getClass());

        PrincipalInfo principalInfo = new PrincipalInfo(principal.getName());

        LOG.info("{}", servletRequest.getClass());

        for (int i = 0; i < ALL_ROLES.length; i++) {
            String role = ALL_ROLES[i];

            if (servletRequest.isUserInRole(role)) {
                principalInfo.addRole(role);
            }

        }

        return principalInfo;

    }


}
