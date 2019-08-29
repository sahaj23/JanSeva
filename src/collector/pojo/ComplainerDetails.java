package collector.pojo;

public class ComplainerDetails {
    private long aadharNumber;
    private String complainerName;
    private String complainerEmail;
    private String complainerAddress;
    private long mobileNumber;

    public ComplainerDetails() {
    }

    public ComplainerDetails(long aadharNumber, String complainerName, String complainerEmail, String complainerAddress, long mobileNumber) {
        this.aadharNumber = aadharNumber;
        this.complainerName = complainerName;
        this.complainerEmail = complainerEmail;
        this.complainerAddress = complainerAddress;
        this.mobileNumber = mobileNumber;
    }

    public long getAadharNumber() {
        return aadharNumber;
    }

    public void setAadharNumber(long aadharNumber) {
        this.aadharNumber = aadharNumber;
    }

    public String getComplainerName() {
        return complainerName;
    }

    public void setComplainerName(String complainerName) {
        this.complainerName = complainerName;
    }

    public String getComplainerEmail() {
        return complainerEmail;
    }

    public void setComplainerEmail(String complainerEmail) {
        this.complainerEmail = complainerEmail;
    }

    public String getComplainerAddress() {
        return complainerAddress;
    }

    public void setComplainerAddress(String complainerAddress) {
        this.complainerAddress = complainerAddress;
    }

    public long getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
    
    
}
