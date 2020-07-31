package uk.co.chonochonov.springfundamentalsworkshop.model.view;

public class UserDetailsViewModel {
    private String username;
    private String email;
    private String git;

    public UserDetailsViewModel() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGit() {
        return git;
    }

    public void setGit(String git) {
        this.git = git;
    }
}
