package it.redhat.demo.rest;

import it.redhat.demo.command.BookCommand;
import it.redhat.demo.command.ProposalCommand;
import it.redhat.demo.entity.ProposalEntity;
import it.redhat.demo.stateless.ProposalStateless;
import org.slf4j.Logger;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by fabio.ercoli@redhat.com on 15/05/17.
 */

@Path("proposal")
public class ProposalRestService {

    @Inject
    private Logger log;

    @Inject
    private ProposalStateless stateless;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Long createProposal(ProposalCommand command) {

        if (command.getAcquire() == null) {
            LocalDate acquire = LocalDate.of(command.getAcquireYear(), command.getAcquireMounth(), command.getAcquireDay());
            command.setAcquire(acquire);
        }

        if (command.getBirth() == null) {
            LocalDate birth = LocalDate.of(command.getBirthYear(), command.getBirthMounth(), command.getBirthDay());
            command.setBirth(birth);
        }

        log.info("saving proposal {}", command);

        return stateless.saveProposal(command);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Long addBook(BookCommand command) {

        log.info("adding book {}", command);

        return stateless.book(command);

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProposalEntity> loadAll() {

        return stateless.getAllProposals();

    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ProposalEntity loadProposal(@PathParam("id") Long id) {

        return stateless.getProposal(id);

    }


}
