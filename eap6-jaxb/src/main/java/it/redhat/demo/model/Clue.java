package it.redhat.demo.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by fabio.ercoli@redhat.com on 23/03/17.
 */
@XmlRootElement
public class Clue {

    private String upper;
    private Long decision;
    private Contract contract;

    public String getUpper() {
        return upper;
    }

    public void setUpper(String upper) {
        this.upper = upper;
    }

    public Long getDecision() {
        return decision;
    }

    public void setDecision(Long decision) {
        this.decision = decision;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }
}
