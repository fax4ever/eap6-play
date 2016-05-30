package it.redhat.demo.security.rest;

import it.redhat.demo.security.model.SecurityInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
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
@Path("user")
@Stateless
public class RestService {

    private Logger log = LoggerFactory.getLogger(RestService.class);

    @Context
    private SecurityContext context;

    @PermitAll
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public SecurityInfo getSecurityContext() {

        log.debug("{}", context);

        SecurityInfo securityInfo = new SecurityInfo();
        Principal principal = context.getUserPrincipal();

        if (principal == null) {
            return securityInfo;
        }

        securityInfo.setUsername(principal.getName());

        return securityInfo;

    }

    @RolesAllowed({"user", "manager"})
    @GET
    @Path("ciao")
    public String ciao() {
        return "ciao";
    }

    @RolesAllowed("manager")
    @GET
    @Path("manager")
    public String ciaoManager() {
        return "ciao manager";
    }

    @RolesAllowed("user")
    @GET
    @Path("user")
    public String ciaoUser() {
        return "ciao user";
    }


}
