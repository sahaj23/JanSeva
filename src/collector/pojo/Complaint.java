package collector.pojo;

import java.util.Date;

public class Complaint {
    private int complaintId;
    private String complaintTitle;
    private String complaintDetails;
    private long aadharNumber;
    private Date complaintDate;
    private String relevantDocument;
    private String collectorNote;

    public Complaint() {
    }

    public Complaint(int complaintId, String complaintTitle, String complaintDetails, long aadharNumber, Date complaintDate, String relevantDocument, String collectorNote) {
        this.complaintId = complaintId;
        this.complaintTitle = complaintTitle;
        this.complaintDetails = complaintDetails;
        this.aadharNumber = aadharNumber;
        this.complaintDate = complaintDate;
        this.relevantDocument = relevantDocument;
        this.collectorNote = collectorNote;
    }

    public int getComplaintId() {
        return complaintId;
    }

    public void setComplaintId(int complaintId) {
        this.complaintId = complaintId;
    }

    public String getComplaintTitle() {
        return complaintTitle;
    }

    public void setComplaintTitle(String complaintTitle) {
        this.complaintTitle = complaintTitle;
    }

    public String getComplaintDetails() {
        return complaintDetails;
    }

    public void setComplaintDetails(String complaintDetails) {
        this.complaintDetails = complaintDetails;
    }

    public long getAadharNumber() {
        return aadharNumber;
    }

    public void setAadharNumber(long aadharNumber) {
        this.aadharNumber = aadharNumber;
    }

    public Date getComplaintDate() {
        return complaintDate;
    }

    public void setComplaintDate(Date complaintDate) {
        this.complaintDate = complaintDate;
    }

    public String getRelevantDocument() {
        return relevantDocument;
    }

    public void setRelevantDocument(String relevantDocument) {
        this.relevantDocument = relevantDocument;
    }

    public String getCollectorNote() {
        return collectorNote;
    }

    public void setCollectorNote(String collectorNote) {
        this.collectorNote = collectorNote;
    }
}
