package collector.pojo;

public class MailStatus {
    private int complaintId;
    private int collectorStatus;
    private int nagarPalikaStatus;
    private int tehsildarStatus;
    private int policeStatus;

    public MailStatus() {
    }

    public MailStatus(int complaintId, int collectorStatus, int nagarPalikaStatus, int tehsildarStatus, int policeStatus) {
        this.complaintId = complaintId;
        this.collectorStatus = collectorStatus;
        this.nagarPalikaStatus = nagarPalikaStatus;
        this.tehsildarStatus = tehsildarStatus;
        this.policeStatus = policeStatus;
    }

    public int getComplaintId() {
        return complaintId;
    }

    public void setComplaintId(int complaintId) {
        this.complaintId = complaintId;
    }

    public int getCollectorStatus() {
        return collectorStatus;
    }

    public void setCollectorStatus(int collectorStatus) {
        this.collectorStatus = collectorStatus;
    }

    public int getNagarPalikaStatus() {
        return nagarPalikaStatus;
    }

    public void setNagarPalikaStatus(int nagarPalikaStatus) {
        this.nagarPalikaStatus = nagarPalikaStatus;
    }

    public int getTehsildarStatus() {
        return tehsildarStatus;
    }

    public void setTehsildarStatus(int tehsildarStatus) {
        this.tehsildarStatus = tehsildarStatus;
    }

    public int getPoliceStatus() {
        return policeStatus;
    }

    public void setPoliceStatus(int policeStatus) {
        this.policeStatus = policeStatus;
    }
    
    
}
