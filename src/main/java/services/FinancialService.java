package services;

import database.ConnectionMysql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JOptionPane;
import models.MovementAccountDTO;

public class FinancialService {
    
    Connection conn;
    PreparedStatement pstm;
    ResultSet rs;
    
    public void createNewMovement(MovementAccountDTO movement, Double new_balance) throws SQLException{
        conn = new ConnectionMysql().connectDB();
        AccountService account_service = new AccountService();

        try {
            String insert = "INSERT INTO movement_account (account_id, typeMovement, price, user_id) VALUES(?, ?, ?, ?);";
            pstm = conn.prepareStatement(insert);
            pstm.setInt(1, movement.getAccount_id());
            pstm.setString(2, movement.getTypeMovement());
            pstm.setDouble(3, movement.getPrice());
            pstm.setInt(4, movement.getUser_id());
            
            pstm.execute();
            account_service.updateAccount(new_balance, movement.getAccount_id());
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Service Movement Create: " + error.getMessage());
        }finally {
            pstm.close();
            conn.close();
        }
    }
    
    public ArrayList<Vector> getAllMovements(int user_id) throws SQLException{
        conn = new ConnectionMysql().connectDB();
        ArrayList<Vector> response = new ArrayList<>();
        
        String select = """
            select
                b.name,
                a.number_account,
                m.typeMovement,
                m.price
            from
                movement_account m
            join accounts a on
                a.id = m.account_id
            join broker b on
                b.id = a.broker_id 
            where
                m.user_id = ?;
        """;
        
        try{
            pstm = conn.prepareStatement(select);
            pstm.setInt(1, user_id);
            rs = pstm.executeQuery();
            
            while (rs.next()) {
                Vector<String> table_row = new Vector<String>();
                table_row.addElement(rs.getString("name"));
                table_row.addElement(rs.getString("number_account"));
                table_row.addElement(rs.getString("typeMovement"));
                table_row.addElement(String.valueOf(rs.getDouble("price")));
                
                response.add(table_row);
            }
            
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Service Movements: " + error.getMessage());
        } finally {
            rs.close();
            conn.close();
        }
        
        return response;
    }
    
}
