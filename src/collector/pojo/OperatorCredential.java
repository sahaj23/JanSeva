package collector.pojo;

public class OperatorCredential {
    private String operatorName;
    private String password;
    private String confidentialEmail;

    public OperatorCredential() {
    }

    public OperatorCredential(String operatorName, String password, String confidentailEmail) {
        this.operatorName = operatorName;
        this.password = password;
        this.confidentialEmail = confidentailEmail;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
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

    public void setConfidentailEmail(String confidentailEmail) {
        this.confidentialEmail = confidentailEmail;
    }
    
}
