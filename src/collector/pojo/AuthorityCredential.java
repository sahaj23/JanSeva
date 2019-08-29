package collector.pojo;

public class AuthorityCredential {
    private String departmentName;
    private String confidentialEmail;
    private String password;

    public AuthorityCredential() {
    }

    public AuthorityCredential(String departmentName, String confidentialEmail, String password) {
        this.departmentName = departmentName;
        this.confidentialEmail = confidentialEmail;
        this.password = password;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getConfidentialEmail() {
        return confidentialEmail;
    }

    public void setConfidentialEmail(String confidentialEmail) {
        this.confidentialEmail = confidentialEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
