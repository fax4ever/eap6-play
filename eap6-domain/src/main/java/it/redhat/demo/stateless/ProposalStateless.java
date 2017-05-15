package it.redhat.demo.stateless;

import it.redhat.demo.command.BookCommand;
import it.redhat.demo.command.ProposalCommand;
import it.redhat.demo.entity.BookValueObject;
import it.redhat.demo.entity.ProposalEntity;
import it.redhat.demo.entity.SubjectEntity;
import org.slf4j.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

/**
 * Created by fabio.ercoli@redhat.com on 15/05/17.
 */

@Stateless
public class ProposalStateless {

    @Inject
    private Logger log;

    @Inject
    private EntityManager em;

    @Inject
    private ZoneId zone;

    public Long saveProposal(ProposalCommand command) {

        Date birth = Date.from(command.getBirth().atStartOfDay(zone).toInstant());
        Date acquire = Date.from(command.getAcquire().atStartOfDay(zone).toInstant());

        ProposalEntity proposal = new ProposalEntity();
        proposal.setStart(new Date());
        proposal.setAmount(command.getAmount());
        proposal.setAcquire(acquire);

        SubjectEntity subject = new SubjectEntity();
        subject.setTaxcode(command.getTaxcode());
        subject.setSurname(command.getSurname());
        subject.setName(command.getName());
        subject.setBirth(birth);

        proposal.setSubject(subject);

        em.persist(proposal);

        return proposal.getId();

    }

    public Long book(BookCommand command) {

        ProposalEntity proposal = em.find(ProposalEntity.class, command.getProposalId());

        BookValueObject book = new BookValueObject();
        book.setInsert(new Date());
        book.setPath(command.getPath());
        book.setUsername(command.getUsername());

        proposal.addBook(book);

        em.merge(proposal);

        return book.getId();

    }

    public ProposalEntity getProposal(Long id) {

        return em.find(ProposalEntity.class, id);

    }

    public List<ProposalEntity> getAllProposals() {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ProposalEntity> cq = cb.createQuery(ProposalEntity.class);
        Root<ProposalEntity> rootEntry = cq.from(ProposalEntity.class);
        CriteriaQuery<ProposalEntity> all = cq.select(rootEntry);
        TypedQuery<ProposalEntity> allQuery = em.createQuery(all);
        return allQuery.getResultList();

    }

}
