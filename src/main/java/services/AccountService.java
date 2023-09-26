package services;

import database.ConnectionMysql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import models.AccountDTO;

public class AccountService {
    
    Connection conn;
    PreparedStatement pstm;
    ResultSet rs;
    ArrayList<AccountDTO> response_accounts = new ArrayList<>();
    
    public void createNewAccount(AccountDTO new_account) throws SQLException {
        conn = new ConnectionMysql().connectDB();

        try {
            String insert = "INSERT INTO accounts (user_id, broker_id, number_account, type_account, balance) VALUES(?, ?, ?, ?, ?);";
            pstm = conn.prepareStatement(insert);
            pstm.setInt(1, new_account.getUser_id());
            pstm.setInt(2, new_account.getBroker_id());
            pstm.setString(3, new_account.getNumber_account());
            pstm.setString(4, new_account.getType_account());
            pstm.setDouble(5, new_account.getBalance());
            
            pstm.execute();
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Service Account Create: " + error.getMessage());
        }finally {
            pstm.close();
            conn.close();
        }
      
    }
    
    public void deleteAccount (int id_account) throws SQLException {
        conn = new ConnectionMysql().connectDB();

        try {
            String delete = "DELETE FROM accounts WHERE id=?;";
            pstm = conn.prepareStatement(delete);
            pstm.setInt(1, id_account);
            pstm.execute();
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Service Accont Delete: " + error.getMessage());
        }finally {
            pstm.close();
            conn.close();
        }
      
    }
    
    public void updateAccount(Double new_balance, int id_account) throws SQLException {
        conn = new ConnectionMysql().connectDB();

        try {
            String update = "UPDATE accounts SET balance=? WHERE id=?;";
            pstm = conn.prepareStatement(update);
            pstm.setDouble(1, new_balance);
            pstm.setInt(2, id_account);
            pstm.execute();
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Service Account Update: " + error.getMessage());
        }finally {
            pstm.close();
            conn.close();
        }
      
    }
    
    public ArrayList<AccountDTO> getAllAccounts(int user_id) throws SQLException{
        conn = new ConnectionMysql().connectDB();
        String select = "select * from accounts where user_id = ?;";
        
        try{
            pstm = conn.prepareStatement(select);
            pstm.setInt(1, user_id);
            rs = pstm.executeQuery();
            
            while (rs.next()) {
                AccountDTO account = new AccountDTO();
                account.setId(rs.getInt("id"));
                account.setUser_id(rs.getInt("user_id"));
                account.setBroker_id(rs.getInt("broker_id"));
                account.setNumber_account(rs.getString("number_account"));
                account.setType_account(rs.getString("type_account"));
                account.setBalance(rs.getDouble("balance"));
                
                response_accounts.add(account);
            }
            
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Service Account: " + error.getMessage());
        } finally {
            rs.close();
            conn.close();
        }
        
        return response_accounts;
    }
    
    public ArrayList<AccountDTO> getAllAccountsToFinancial(int user_id) throws SQLException{
        conn = new ConnectionMysql().connectDB();
        
        String select = """
            select
            	a.id,
            	b.name,
            	a.number_account,
            	a.type_account,
            	a.balance
            from
            	accounts a
            join broker b on
            	b.id  = a.broker_id
            where 
            	a.type_account = 'Conta Real'
            	and a.user_id = ?
        """;
        
        try{
            pstm = conn.prepareStatement(select);
            pstm.setInt(1, user_id);
            rs = pstm.executeQuery();
            
            while (rs.next()) {
                AccountDTO account = new AccountDTO();
                int id_account = rs.getInt("id");
                String name_broker = rs.getString("name");
                String number_account = rs.getString("number_account");
                String type_account = rs.getString("type_account");
                String balance = String.valueOf(rs.getDouble("balance"));
                
                String join_text = "Corretora: " + name_broker + " | Conta: " + number_account + " (" + type_account + ") | Saldo: " + balance;
                
                account.setId(id_account);
                account.setNumber_account(join_text);
                account.setType_account(rs.getString("type_account"));
                account.setBalance(rs.getDouble("balance"));
                
                response_accounts.add(account);
            }
            
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Service Account: " + error.getMessage());
        } finally {
            rs.close();
            conn.close();
        }
        
        return response_accounts;
    }
    
    public ArrayList<AccountDTO> getAllAccountsToCreateOrders(int user_id) throws SQLException{
        conn = new ConnectionMysql().connectDB();
        
        String select = """
            select
            	a.id,
            	b.name,
            	a.number_account,
            	a.type_account,
            	a.balance
            from
            	accounts a
            join broker b on
            	b.id  = a.broker_id
            where
            	a.user_id = ?
        """;
        
        try{
            pstm = conn.prepareStatement(select);
            pstm.setInt(1, user_id);
            rs = pstm.executeQuery();
            
            while (rs.next()) {
                AccountDTO account = new AccountDTO();
                int id_account = rs.getInt("id");
                String name_broker = rs.getString("name");
                String number_account = rs.getString("number_account");
                String type_account = rs.getString("type_account");
                String balance = String.valueOf(rs.getDouble("balance"));
                
                String join_text = "Corretora: " + name_broker + " | Conta: " + number_account + " (" + type_account + ") | Saldo: " + balance;
                
                account.setId(id_account);
                account.setNumber_account(join_text);
                account.setType_account(rs.getString("type_account"));
                account.setBalance(rs.getDouble("balance"));
                
                response_accounts.add(account);
            }
            
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Service Account: " + error.getMessage());
        } finally {
            rs.close();
            conn.close();
        }
        
        return response_accounts;
    }
    
    
    public Map<Integer, Double> getBalanceOfAccount(int id_order) throws SQLException{
        conn = new ConnectionMysql().connectDB();
        Map<Integer, Double> account = new HashMap<Integer, Double>();
        
        String select = """
            select
                id,
                balance
            from
                accounts
            where
                id = (
                select
                    o.account_id
                from
                    orders o
                where
                    o.id = ?);
        """;
        
        try{
            pstm = conn.prepareStatement(select);
            pstm.setInt(1, id_order);
            rs = pstm.executeQuery();
            
            while (rs.next()) {
                account.put(rs.getInt("id"), rs.getDouble("balance"));
            }
            
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Service Get Balance Account: " + error.getMessage());
        } finally {
            rs.close();
            conn.close();
        }
        
        return account;
    }
    
}
