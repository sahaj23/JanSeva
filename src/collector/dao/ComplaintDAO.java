package collector.dao;

import collector.dbutil.DBConnection;
import collector.pojo.Complaint;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class ComplaintDAO {
   
    public static int getNextID() throws SQLException {
        Connection conn = DBConnection.getConnection();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("select count(*) from complaint_register");
        rs.next();
        return rs.getInt(1) + 101;
    }

    public static boolean registerComplaint(Complaint complaint) throws SQLException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("insert into complaint_register values(?,?,?,?,?,?,?)");
        ps.setInt(1, getNextID());
        ps.setString(2, complaint.getComplaintTitle());
        ps.setString(3, complaint.getComplaintDetails());
        ps.setString(4, Long.toString(complaint.getAadharNumber()));
        long ms = complaint.getComplaintDate().getTime();
        java.sql.Date currDate = new java.sql.Date(ms);
        ps.setDate(5, currDate);
        ps.setString(6, complaint.getRelevantDocument());
        ps.setString(7, complaint.getCollectorNote());
        int ans = ps.executeUpdate();
        return ans == 1;
    }
}
