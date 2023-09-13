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
    
    public void createNewBroker(String broker_name) {
        conn = new ConnectionMysql().connectDB();

        try {
            String insert = "INSERT INTO grupo5_willian.broker (name) VALUES(?);";
            pstm = conn.prepareStatement(insert);
            pstm.setString(1, broker_name);
            pstm.execute();
            pstm.close();
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Service Usuario: " + error.getMessage());
        }
    }
    
    public ArrayList<BrokerDTO> getAllBrokers() throws SQLException{
        conn = new ConnectionMysql().connectDB();
        String select = "select * from grupo5_willian.broker;";
        
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
