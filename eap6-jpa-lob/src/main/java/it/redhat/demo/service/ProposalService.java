package it.redhat.demo.service;

import it.redhat.demo.entity.Proposal;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.ws.rs.GET;
import java.util.List;


@Stateless
public class ProposalService {

	@Inject
	private EntityManager em;

    @GET
    public List<Proposal> all() {

        CriteriaQuery<Proposal> criteria = em.getCriteriaBuilder()
                .createQuery(Proposal.class);

        Root<Proposal> root = criteria.from(Proposal.class);
        CriteriaQuery<Proposal> select = criteria.select(root);

        return em.createQuery(select).getResultList();

    }

    public Proposal get(Long id) {

        return em.find(Proposal.class, id);

    }

    public Proposal create(Proposal person) {

        em.persist(person);
        return person;

    }

	public Proposal update(Proposal person) {
		
		em.merge(person);
		return person;
		
	}

    public void delete(Long id) {

        Proposal proposal = em.find(Proposal.class, id);
        em.remove(proposal);

    }


}
