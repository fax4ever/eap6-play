package it.redhat.demo.rest;

import it.redhat.demo.domain.Member;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * @author Fabio Massimo Ercoli
 *         fabio.ercoli@redhat.com
 *         on 09/06/16
 */
@Path("member")
@Stateless
public class MemberRestService {

    @Inject
    private EntityManager em;

    @GET
    public String letsGo() {
        return "let's go! member!";
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Member get(@PathParam("id") Integer id) {

        return em.find(Member.class, id);

    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void insert(Member member) {

        em.persist(member);

    }

}
