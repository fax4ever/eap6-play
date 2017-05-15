package it.redhat.demo.command;

/**
 * Created by fabio.ercoli@redhat.com on 15/05/17.
 */
public class BookCommand {
    private Long proposalId;
    private String path;
    private String username;

    public Long getProposalId() {
        return proposalId;
    }

    public void setProposalId(Long proposalId) {
        this.proposalId = proposalId;
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
}
