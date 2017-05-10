package it.redhat.demo.entity;

import javax.persistence.*;

/**
 * Created by fabio.ercoli@redhat.com on 10/05/17.
 */

@Entity
@Table(name = "Proposal")
public class ProposalEntity {

    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.AUTO, generator = "PROPOSAL_ID_GENERATOR")
    @SequenceGenerator(sequenceName = "PROPOSAL_ID_SEQ", name = "PROPOSAL_ID_GENERATOR")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "subject")
    private SubjectEntity subject;

}
