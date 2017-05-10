package t.redhat.demo.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by fabio.ercoli@redhat.com on 10/05/17.
 */
public class Person implements Serializable {

    private String surname;
    private String name;
    private Date birth;
    private String jobTitle;
    private Integer salary;

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (surname != null ? !surname.equals(person.surname) : person.surname != null) return false;
        if (name != null ? !name.equals(person.name) : person.name != null) return false;
        if (birth != null ? !birth.equals(person.birth) : person.birth != null) return false;
        if (jobTitle != null ? !jobTitle.equals(person.jobTitle) : person.jobTitle != null) return false;
        return salary != null ? salary.equals(person.salary) : person.salary == null;

    }

    @Override
    public int hashCode() {
        int result = surname != null ? surname.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (birth != null ? birth.hashCode() : 0);
        result = 31 * result + (jobTitle != null ? jobTitle.hashCode() : 0);
        result = 31 * result + (salary != null ? salary.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Person{" +
                "surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", birth=" + birth +
                ", jobTitle='" + jobTitle + '\'' +
                ", salary=" + salary +
                '}';
    }

}
