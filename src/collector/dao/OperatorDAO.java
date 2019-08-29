package collector.dao;

import collector.dbutil.DBConnection;
import collector.pojo.OperatorCredential;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OperatorDAO {

    public static boolean verifyUserEndOperator(OperatorCredential operator) throws SQLException {
        PreparedStatement ps = DBConnection.getConnection().prepareStatement("Select operator_name from user_end_operator where operator_name = ? and password = ?");
        ps.setString(1, operator.getOperatorName());
        ps.setString(2,operator.getPassword());
        ResultSet rs = ps.executeQuery();
        if(rs.next())
            return true;
        else
            return false;
    }
    public static boolean registerUserEndOperator(OperatorCredential operator) throws SQLException {
        PreparedStatement ps = DBConnection.getConnection().prepareStatement("insert into user_end_operator values (?,?,?)");
        ps.setString(1, operator.getOperatorName());
        ps.setString(2, operator.getPassword());
        ps.setString(3, operator.getConfidentialEmail());
        return ps.executeUpdate() == 1;
    }
    public static boolean isNewUserEndOperatorPassword(OperatorCredential operator) throws SQLException {
        OperatorCredential old = getUserEndOperator(operator.getOperatorName());
        if(old.getPassword().equalsIgnoreCase(operator.getPassword()))
            return false;
        else
            return true;
    }
    public static boolean verifyOperator(OperatorCredential operator) throws SQLException {
        PreparedStatement ps = DBConnection.getConnection().prepareStatement("Select * from user_end_operator where operator_name = ? and confidential_email = ?");
        ps.setString(1, operator.getOperatorName());
        ps.setString(2, operator.getConfidentialEmail());
        ResultSet rs = ps.executeQuery();
        return rs.next();
    }
    public static boolean changeOperatorPassword(OperatorCredential operator) throws SQLException {
        PreparedStatement ps = DBConnection.getConnection().prepareStatement("update user_end_operator set password = ? where operator_name = ?");
        ps.setString(1, operator.getPassword());
        ps.setString(2, operator.getOperatorName());
        return ps.executeUpdate() == 1;
    }   
    public static OperatorCredential getUserEndOperator(String operatorName) throws SQLException {
        PreparedStatement ps = DBConnection.getConnection().prepareStatement("Select * from user_end_operator where operator_name = ?");
        ps.setString(1, operatorName);
        ResultSet rs = ps.executeQuery();
        rs.next();
        return new OperatorCredential(rs.getString(1),rs.getString(2),rs.getString(3));
    }
    
    public static boolean checkSolver(String solverName) throws SQLException {
        PreparedStatement ps = DBConnection.getConnection().prepareStatement("Select * from solver_end where operator_name = ?");
        ps.setString(1, solverName);
        ResultSet rs = ps.executeQuery();
        return rs.next();
    }
    public static boolean registerSolver(OperatorCredential solver) throws SQLException {
        PreparedStatement ps = DBConnection.getConnection().prepareStatement("insert into solver_end values(?,?,?)");
        ps.setString(1, solver.getOperatorName());
        ps.setString(2, solver.getPassword());
        ps.setString(3, solver.getConfidentialEmail());
        return ps.executeUpdate() == 1;
    }
    public static boolean verifySolver(OperatorCredential solver) throws SQLException {
        PreparedStatement ps = DBConnection.getConnection().prepareStatement("Select * from solver_end where operator_name = ? and password = ?");
        ps.setString(1, solver.getOperatorName());
        ps.setString(2, solver.getPassword());
        ResultSet rs = ps.executeQuery();
        return rs.next();
    }
    public static boolean changeSolverPassword(OperatorCredential solver) throws SQLException {
        PreparedStatement ps = DBConnection.getConnection().prepareStatement("update solver_end set password = ? where operator_name = ?");
        ps.setString(1, solver.getPassword());
        ps.setString(2, solver.getOperatorName());
        return ps.executeUpdate() == 1;
    } 
}
