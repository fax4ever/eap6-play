package it.redhat.demo.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by fabio.ercoli@redhat.com on 15/05/17.
 */

@Entity
@Table(name = "Book")
public class BookValueObject {

    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.AUTO, generator = "BOOK_ID_GENERATOR")
    @SequenceGenerator(sequenceName = "BOOK_ID_SEQ", name = "BOOK_ID_GENERATOR")
    private Long id;

    @Column(unique=true)
    private String path;

    private String username;
    private Date insert;

    public Long getId() {
        return id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getInsert() {
        return insert;
    }

    public void setInsert(Date insert) {
        this.insert = insert;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookValueObject that = (BookValueObject) o;

        if (path != null ? !path.equals(that.path) : that.path != null) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        return insert != null ? insert.equals(that.insert) : that.insert == null;

    }

    @Override
    public int hashCode() {
        int result = path != null ? path.hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (insert != null ? insert.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BookValueObject{" +
                "id=" + id +
                ", path='" + path + '\'' +
                ", username='" + username + '\'' +
                ", insert=" + insert +
                '}';
    }

}
