package collector.dao;

import collector.dbutil.DBConnection;
import collector.pojo.CollectorCredential;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CollectorDAO {
    public static boolean verifyCollector(String password) throws SQLException {
        PreparedStatement ps = DBConnection.getConnection().prepareStatement("Select * from collector where password = ?");
        ps.setString(1, password);
        ResultSet rs = ps.executeQuery();
        return rs.next();
    }
    public static boolean checkCollector(CollectorCredential collector) throws SQLException {
        PreparedStatement ps = DBConnection.getConnection().prepareStatement("Select * from collector where confidential_email = ?");
        ps.setString(1, collector.getConfidentialEmail());
        ResultSet rs = ps.executeQuery();
        return rs.next();
    }
    public static boolean registerCollector(CollectorCredential collector) throws SQLException {
        PreparedStatement ps = DBConnection.getConnection().prepareStatement("insert into collector values(?,?)");
        ps.setString(1, collector.getPassword());
        ps.setString(2, collector.getConfidentialEmail());
        return ps.executeUpdate() == 1;
    }
    public static boolean changePassword(CollectorCredential collector) throws SQLException {
        if(checkCollector(collector)) {
            PreparedStatement ps = DBConnection.getConnection().prepareStatement("Update collector set password = ?");
            ps.setString(1, collector.getPassword());
            return ps.executeUpdate() == 1;
        }
        else
            return false;
    }
    public static CollectorCredential getConfidentialMail() throws SQLException {
        Statement st = DBConnection.getConnection().createStatement();
        ResultSet rs = st.executeQuery("Select * from all_authorities where dept_name = 'JilaCollector'");
        rs.next();
        return new CollectorCredential(rs.getString(3), rs.getString(2));
    }
}
