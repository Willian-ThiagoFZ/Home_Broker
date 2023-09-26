package services;

import database.ConnectionMysql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import models.MovementAccountDTO;
import models.OrderBuyStock;

public class OrderService {
    Connection conn;
    PreparedStatement pstm;
    ResultSet rs;
    ArrayList<OrderBuyStock> response_orders = new ArrayList<>();
    
    private void createMovements(Double new_balance, int account_id, String typeMovement, int user_id, double price){
        FinancialService service = new FinancialService();
        MovementAccountDTO movement = new MovementAccountDTO();
        try {
            movement.setAccount_id(account_id);
            movement.setTypeMovement(typeMovement);
            movement.setUser_id(user_id);
            movement.setPrice(price);
            service.createNewMovement(movement, new_balance);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Criar um Movimento: " + ex);
        }
    }
    
    public void createNewOrder(OrderBuyStock order, Double new_balance, double pay_price) throws SQLException{
        conn = new ConnectionMysql().connectDB();

        try {
            String insert = "INSERT INTO orders (account_id, quantity, stock, buy_price, total_amount_invest, user_id) VALUES(?, ?, ?, ?, ?, ?);";
            pstm = conn.prepareStatement(insert);
            pstm.setInt(1, order.getAccount_id());
            pstm.setInt(2, order.getQuantity());
            pstm.setString(3, order.getStock());
            pstm.setDouble(4, order.getBuy_price());
            pstm.setDouble(5, order.getTotal_amount_invest());
            pstm.setInt(6, order.getUser_id());
            
            pstm.execute();
            createMovements(new_balance, order.getAccount_id(), "Compra", order.getUser_id(), pay_price);
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Service Order Create: " + error.getMessage());
        }finally {
            pstm.close();
            conn.close();
        }
    }
    
    public void updateOrder(OrderBuyStock order, Double new_balance) throws SQLException {
        conn = new ConnectionMysql().connectDB();

        try {
            String update = "UPDATE orders SET sold_price=?, total_amount_returns=?, open_operation=0 WHERE id=?;";
            pstm = conn.prepareStatement(update);
            pstm.setDouble(1, order.getSold_price());
            pstm.setDouble(2, order.getTotal_amount_returns());
            pstm.setInt(3, order.getId());
            pstm.execute();
            
            Double pay_price = order.getQuantity() * order.getSold_price();
            createMovements(new_balance, order.getAccount_id(), "Venda", order.getUser_id(), pay_price);
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Service Account Update: " + error.getMessage());
        }finally {
            pstm.close();
            conn.close();
        }
      
    }
    
    public ArrayList<OrderBuyStock> getAllOrders(int user_id, int orders_opened) throws SQLException{
        conn = new ConnectionMysql().connectDB();
        String select = "select id, quantity, stock, buy_price, total_amount_invest, sold_price, total_amount_returns from orders where user_id = ? and open_operation = ?;";
        
        try{
            pstm = conn.prepareStatement(select);
            pstm.setInt(1, user_id);
            pstm.setInt(2, orders_opened);
            rs = pstm.executeQuery();
            
            while (rs.next()) {
                OrderBuyStock order = new OrderBuyStock();
                order.setId(rs.getInt("id"));
                order.setQuantity(rs.getInt("quantity"));
                order.setStock(rs.getString("stock"));
                order.setBuy_price(rs.getDouble("buy_price"));
                order.setTotal_amount_invest(rs.getDouble("total_amount_invest"));
                order.setSold_price(rs.getDouble("sold_price"));
                order.setTotal_amount_returns(rs.getDouble("total_amount_returns"));
                
                response_orders.add(order);
            }
            
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Order Service gettall: " + error.getMessage());
        } finally {
            rs.close();
            conn.close();
        }
        
        return response_orders;
    }
}
