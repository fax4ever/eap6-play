package it.redhat.demo.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

    @Temporal(TemporalType.DATE)
    private Date acquire;

    @Temporal(TemporalType.DATE)
    private Date start;

    @OneToMany(cascade = CascadeType.ALL)
    private List<BookValueObject> books = new ArrayList<>();

    private Integer amount;

    public Long getId() {
        return id;
    }

    public SubjectEntity getSubject() {
        return subject;
    }

    public void setSubject(SubjectEntity subject) {
        this.subject = subject;
    }

    public Date getAcquire() {
        return acquire;
    }

    public void setAcquire(Date acquire) {
        this.acquire = acquire;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public void addBook(BookValueObject book) {
        this.books.add(book);
    }

    public int removeBookByPath(String path) {

        List<BookValueObject> collect = books.stream()
                .filter(book -> path.equals(book.getPath()))
                .collect(Collectors.toList());

        books.removeAll(collect);
        return collect.size();

    }

    public int removeBookByUsername(String username) {

        List<BookValueObject> collect = books.stream()
                .filter(book -> username.equals(book.getUsername()))
                .collect(Collectors.toList());

        books.removeAll(collect);
        return collect.size();

    }

    public int removeBookByDates(Date from, Date to) {

        List<BookValueObject> collect = books.stream()
                .filter(book -> from.compareTo(book.getInsert()) <= 0)
                .filter(book -> to.compareTo(book.getInsert()) >= 0)
                .collect(Collectors.toList());

        books.removeAll(collect);
        return collect.size();

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProposalEntity that = (ProposalEntity) o;

        return id != null ? id.equals(that.id) : that.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "ProposalEntity{" +
                "id=" + id +
                ", subject=" + subject +
                ", acquire=" + acquire +
                ", start=" + start +
                ", amount=" + amount +
                '}';
    }

}
