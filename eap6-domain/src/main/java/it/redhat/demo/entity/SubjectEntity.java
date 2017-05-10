package it.redhat.demo.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by fabio.ercoli@redhat.com on 10/05/17.
 */

@Entity
@Table(name = "Subject")
public class SubjectEntity {

    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.AUTO, generator = "SUBJECT_ID_GENERATOR")
    @SequenceGenerator(sequenceName = "SUBJECT_ID_SEQ", name = "SUBJECT_ID_GENERATOR")
    private Long id;

    private String fiscalCode;

    private String name;

    private String surname;

    @Temporal(TemporalType.DATE)
    private Date birth;

}
