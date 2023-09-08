
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ConnectionMysql {
    
    public Connection connectDB(){
        Connection conn = null;
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionMysql.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try{
            //String url = "jdbc:mysql://200.195.178.52/grupo5_willian?user=grupo5&password=grupo5";
            String url = "jdbc:mysql://localhost/grupo5_willian?user=root&password=adminroot";
            conn = DriverManager.getConnection(url);
        }catch (SQLException error){
            JOptionPane.showMessageDialog(null, "Error on Conect in Database: " + error.getMessage());
        }
        
        return conn;
    }
    
}
