package it.redhat.demo.rest;

import it.redhat.demo.service.CacheInfoService;
import org.infinispan.remoting.transport.Address;
import org.jgroups.stack.IpAddress;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

/**
 * @author Fabio Massimo Ercoli
 *         fabio.ercoli@redhat.com
 *         on 09/06/16
 */

@Path("")
@Produces(MediaType.APPLICATION_JSON)
public class RestService {

    @Inject
    private CacheInfoService service;

    @GET
    public String letsGo() {
        return "start";
    }

    @GET
    @Path("jgroups")
    public Collection<IpAddress> jgroups() {
        return service.jgroups();
    }

    @GET
    @Path("infinispan")
    public Collection<Address> infinispan() {
        return service.infinispan();
    }

    @GET
    @Path("jmx")
    public Object jmx() {
        return service.jmx();
    }

}
