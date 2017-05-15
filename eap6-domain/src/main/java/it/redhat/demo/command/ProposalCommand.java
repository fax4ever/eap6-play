package it.redhat.demo.command;

import java.time.LocalDate;

/**
 * Created by fabio.ercoli@redhat.com on 15/05/17.
 */
public class ProposalCommand {

    private String taxcode;
    private LocalDate acquire;
    private Integer amount;
    private String surname;
    private String name;
    private LocalDate birth;

    public String getTaxcode() {
        return taxcode;
    }

    public void setTaxcode(String taxcode) {
        this.taxcode = taxcode;
    }

    public LocalDate getAcquire() {
        return acquire;
    }

    public void setAcquire(LocalDate acquire) {
        this.acquire = acquire;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

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

    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProposalCommand that = (ProposalCommand) o;

        if (taxcode != null ? !taxcode.equals(that.taxcode) : that.taxcode != null) return false;
        if (acquire != null ? !acquire.equals(that.acquire) : that.acquire != null) return false;
        if (amount != null ? !amount.equals(that.amount) : that.amount != null) return false;
        if (surname != null ? !surname.equals(that.surname) : that.surname != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return birth != null ? birth.equals(that.birth) : that.birth == null;

    }

    @Override
    public int hashCode() {
        int result = taxcode != null ? taxcode.hashCode() : 0;
        result = 31 * result + (acquire != null ? acquire.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (birth != null ? birth.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ProposalCommand{" +
                "taxcode='" + taxcode + '\'' +
                ", acquire=" + acquire +
                ", amount=" + amount +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", birth=" + birth +
                '}';
    }

}
