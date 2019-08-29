package collector.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class DBConnection {
    private static Connection conn;
    static{
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            System.out.println("driver loaded successfully");
             conn = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/XE","project","project");
//             JOptionPane.showMessageDialog(null,"Connection Successful!!");
            
        }
        catch(Exception ex){
                ex.printStackTrace();
                
                JOptionPane.showMessageDialog(null,"Connection UnSuccessful!!");
      
        }
    }
    public static Connection getConnection(){
        return conn;
    }
    public static void closeConnection(){
        try{
            conn.close();
         JOptionPane.showMessageDialog(null,"disconnected Successfully!!");
      
        }
        catch(Exception ex){
             JOptionPane.showMessageDialog(null,"disconnection UnSuccessful!!");
      
          ex.printStackTrace();
        }
    }
}
