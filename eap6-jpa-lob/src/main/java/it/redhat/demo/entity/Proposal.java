package it.redhat.demo.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created by fabio.ercoli@redhat.com on 13/04/17.
 */

@Entity
public class Proposal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="proposalIdSeq")
    @SequenceGenerator(name="proposalIdSeq", sequenceName="PROPOSAL_ID_SEQ", allocationSize=1)
    private Long id;

    @Size(min=3, max=9)
    @Pattern(regexp = "[A-Za-z ]*", message = "must contain only letters and spaces")
    @NotNull
    @Column(nullable = false)
    private String title;

    @Lob
    @NotNull
    @Column(length = 65535)
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
