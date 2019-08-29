package collector.pojo;

public class CollectorCredential {
    private String password;
    private String confidentialEmail;

    public CollectorCredential() {
    }

    public CollectorCredential(String password, String confidentialEmail) {
        this.password = password;
        this.confidentialEmail = confidentialEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfidentialEmail() {
        return confidentialEmail;
    }

    public void setConfidentialEmail(String confidentialEmail) {
        this.confidentialEmail = confidentialEmail;
    }
    
}
