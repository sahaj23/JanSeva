package collector.dao;

import collector.dbutil.DBConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MailStatusDAO {
    public static boolean checkEntry(int complaint_id) throws SQLException {
        PreparedStatement ps = DBConnection.getConnection().prepareStatement("Select * from mail_status where complaint_id = ?");
        ps.setInt(1, complaint_id);
        ResultSet rs = ps.executeQuery();
        if(rs.next())
            return true;
        else 
            return false;
    }
    public static boolean createCollectorEntry(int complaint_id) throws SQLException {
        PreparedStatement ps = DBConnection.getConnection().prepareStatement("insert into mail_status values(?,?,?,?,?)");
        ps.setInt(1,complaint_id);
        ps.setInt(2, 0);
        ps.setInt(3, 0);
        ps.setInt(4, 0);
        ps.setInt(5, 0);
        int res = ps.executeUpdate();
        return res == 1;
    }
    public static int getCollectorStatus(int complaint_id) throws SQLException {
        PreparedStatement ps = DBConnection.getConnection().prepareStatement("Select collector_status from mail_status where complaint_id = ?");
        ps.setInt(1,complaint_id);
        ResultSet rs = ps.executeQuery();
        rs.next();
        return rs.getInt(1);
    }
    public static boolean setCollectorStatus(int complaint_id, int status) throws SQLException {
        PreparedStatement ps = DBConnection.getConnection().prepareStatement("update mail_status set collector_status = ? where complaint_id = ?");
        ps.setInt(1,status);
        ps.setInt(2, complaint_id);
        int res = ps.executeUpdate();
        return res == 1;
    }
    public static int getAuthorityStatus(int complaint_id, String departmentName) throws SQLException {
        String qry = "Select "+departmentName+"_status from mail_status where complaint_id = ?";
        PreparedStatement ps = DBConnection.getConnection().prepareStatement(qry);
        ps.setInt(1,complaint_id);
        ResultSet rs = ps.executeQuery();
        rs.next();
        return rs.getInt(1);
    }
    public static boolean setAuthorityStatus(int complaint_id, String departmentName, int status) throws SQLException {
        String qry = "update mail_status set "+departmentName+"_status = ? where complaint_id = ?";
        PreparedStatement ps = DBConnection.getConnection().prepareStatement(qry);
        ps.setInt(1,status);
        ps.setInt(2, complaint_id);
        int res = ps.executeUpdate();
        return res == 1;
    }
}