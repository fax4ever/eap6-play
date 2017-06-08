package it.redhat.demo.roles.rest;

import it.redhat.demo.roles.model.PrincipalInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
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

    private final static String[] ALL_ROLES = { "admin", "AGENTE", "AMMINISTRATORE", "analyst", "DIREZIONALE", "kie-server", "manager", "MEDICO", "VAGE-GI", "VASE-GI", "VASS-GI", "VAUT-GI", "VCM1-GI", "VCM2-GI", "VENDITORE", "VVAC-GI", "VVCC-GI", "VVSC-GI" };

    @Context
    private SecurityContext securityContext;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public PrincipalInfo getSecurityContext() {

        if (securityContext == null) {
            return new PrincipalInfo();
        }

        Principal principal = securityContext.getUserPrincipal();

        if (principal == null) {
            return new PrincipalInfo();
        }

        LOG.info("{}", principal.getClass());

        PrincipalInfo principalInfo = new PrincipalInfo(principal.getName());

        for (int i = 0; i < ALL_ROLES.length; i++) {
            String role = ALL_ROLES[i];

            if (securityContext.isUserInRole(role)) {
                principalInfo.addRole(role);
            }

        }

        return principalInfo;

    }


}
