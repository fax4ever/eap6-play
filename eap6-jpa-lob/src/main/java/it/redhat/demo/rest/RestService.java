package it.redhat.demo.rest;

import it.redhat.demo.entity.Proposal;
import it.redhat.demo.service.ProposalService;
import org.jboss.resteasy.spi.validation.ValidateRequest;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by fabio.ercoli@redhat.com on 13/04/17.
 */

@Path("proposal")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RestService {

    @Inject
    private ProposalService service;

    @GET
    public List<Proposal> all() {

        return service.all();

    }

    @GET
    @Path("{id}")
    public Proposal get(@PathParam("id") Long id) {

        return service.get(id);

    }

    @POST
    @ValidateRequest
    public Proposal create(@Valid Proposal person) {

        return service.create(person);

    }

    @PUT
    @ValidateRequest
    public Proposal update(@Valid Proposal person) {

        return service.update(person);

    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") Long id) {

        service.delete(id);

    }

}
