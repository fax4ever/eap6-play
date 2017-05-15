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

    private String taxcode;

    private String name;

    private String surname;

    @Temporal(TemporalType.DATE)
    private Date birth;

    public Long getId() {
        return id;
    }

    public String getTaxcode() {
        return taxcode;
    }

    public void setTaxcode(String taxcode) {
        this.taxcode = taxcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SubjectEntity that = (SubjectEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (taxcode != null ? !taxcode.equals(that.taxcode) : that.taxcode != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (surname != null ? !surname.equals(that.surname) : that.surname != null) return false;
        return birth != null ? birth.equals(that.birth) : that.birth == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (taxcode != null ? taxcode.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (birth != null ? birth.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SubjectEntity{" +
                "id=" + id +
                ", taxcode='" + taxcode + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birth=" + birth +
                '}';
    }

}
