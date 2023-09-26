package services;

import database.ConnectionMysql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import models.BrokerDTO;

public class BrokerService {
    
    Connection conn;
    PreparedStatement pstm;
    ResultSet rs;
    ArrayList<BrokerDTO> response_brokers = new ArrayList<>();
    
    public void createNewBroker(String broker_name) throws SQLException {
        conn = new ConnectionMysql().connectDB();

        try {
            String insert = "INSERT INTO broker (name) VALUES(?);";
            pstm = conn.prepareStatement(insert);
            pstm.setString(1, broker_name);
            pstm.execute();
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Service Usuario Create: " + error.getMessage());
        }finally {
            pstm.close();
            conn.close();
        }
      
    }
    
    public void updateBroker(String broker_name, int id_broker) throws SQLException {
        conn = new ConnectionMysql().connectDB();

        try {
            String update = "UPDATE broker SET name=? WHERE id=?;";
            pstm = conn.prepareStatement(update);
            pstm.setString(1, broker_name);
            pstm.setInt(2, id_broker);
            pstm.execute();
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Service Usuario Update: " + error.getMessage());
        }finally {
            pstm.close();
            conn.close();
        }
      
    }
    
    public void deleteBroker(int id_broker) throws SQLException {
        conn = new ConnectionMysql().connectDB();

        try {
            String delete = "DELETE FROM broker WHERE id=?;";
            pstm = conn.prepareStatement(delete);
            pstm.setInt(1, id_broker);
            pstm.execute();
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Service Broker Delete: " + error.getMessage());
        }finally {
            pstm.close();
            conn.close();
        }
      
    }
    
    public ArrayList<BrokerDTO> getAllBrokers() throws SQLException{
        conn = new ConnectionMysql().connectDB();
        String select = "select * from broker order by name;";
        
        try{
            pstm = conn.prepareStatement(select);
            rs = pstm.executeQuery();
            
            while (rs.next()) {
                BrokerDTO broker = new BrokerDTO();
                broker.setId(rs.getInt("id"));
                broker.setName(rs.getString("name"));
                
                response_brokers.add(broker);
            }
            
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Service Usuario: " + error.getMessage());
        } finally {
            rs.close();
            conn.close();
        }
        
        return response_brokers;
    }
    
}
