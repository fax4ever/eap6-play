package it.redhat.demo.stateless;

import it.redhat.demo.command.ProposalCommand;
import it.redhat.demo.entity.ProposalEntity;
import it.redhat.demo.entity.SubjectEntity;
import org.slf4j.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.time.ZoneId;
import java.util.Date;

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

    public void saveProposal(ProposalCommand command) {

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

        em.persist(proposal);

    }

}
