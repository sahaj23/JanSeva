package collector.dao;

import collector.dbutil.DBConnection;
import collector.pojo.AuthorityCredential;
import collector.pojo.OperatorCredential;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AuthorityDAO {
    public static boolean verifyConfidentialMail(String email) throws SQLException {
        PreparedStatement ps = DBConnection.getConnection().prepareStatement("Select * from all_authorities where confidential_email = ?");
        ps.setString(1,email);
        ResultSet rs = ps.executeQuery();
        return rs.next();
    }
    public static AuthorityCredential getOperatorAuthority(OperatorCredential operator) throws SQLException {
        PreparedStatement ps1 = DBConnection.getConnection().prepareStatement("Select confidential_email from user_end_operator where operator_name = ?");
        PreparedStatement ps2 = DBConnection.getConnection().prepareStatement("Select * from all_authorities where confidential_email = ?");
        ps1.setString(1, operator.getOperatorName());
        ResultSet rs = ps1.executeQuery();
        rs.next(); 
        ps2.setString(1, rs.getString(1));
        rs = ps2.executeQuery();
        rs.next();
        return new AuthorityCredential(rs.getString(1),rs.getString(2),rs.getString(3));
    }
    public static AuthorityCredential getSolverAuthority(OperatorCredential solver) throws SQLException {
        PreparedStatement ps1 = DBConnection.getConnection().prepareStatement("Select confidential_email from solver_end where operator_name = ?");
        PreparedStatement ps2 = DBConnection.getConnection().prepareStatement("Select * from all_authorities where confidential_email = ?");
        ps1.setString(1, solver.getOperatorName());
        ResultSet rs = ps1.executeQuery();
        rs.next(); 
        ps2.setString(1, rs.getString(1));
        rs = ps2.executeQuery();
        rs.next();
        return new AuthorityCredential(rs.getString(1),rs.getString(2),rs.getString(3));
    }
    public static ArrayList<String> getAllAuthorities() throws SQLException 
    {
        Statement st = DBConnection.getConnection().createStatement();
        ArrayList<String> departments = new ArrayList<>();
        ResultSet rs = st.executeQuery("select dept_name from all_authorities where dept_name != 'JilaCollector'");
        while(rs.next())
            departments.add(rs.getString(1));
        return departments;
    }
    public static String getAuthorityEmail(String departmentName) throws SQLException {
        PreparedStatement ps = DBConnection.getConnection().prepareStatement("Select confidential_email from all_authorities where dept_name = ?");
        ps.setString(1, departmentName);
        ResultSet rs = ps.executeQuery();
        rs.next();
        return rs.getString(1);
    }
}