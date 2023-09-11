package services;

import database.ConnectionMysql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import models.UserDTO;

public class UserService {

    Connection conn;
    PreparedStatement pstm;

    public ResultSet authenticateUSer(UserDTO obj_user) throws SQLException {
        conn = new ConnectionMysql().connectDB();

        try {
            String sql = "SELECT * FROM users WHERE email = ? AND password = ?;";
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, obj_user.getEmail());
            pstm.setString(2, obj_user.getPassword());

            ResultSet rs = pstm.executeQuery();
            return rs;
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Service Usuario: " + error.getMessage());
            return null;
        }

    }

    public boolean validUSerEmail(String email) throws SQLException {
        conn = new ConnectionMysql().connectDB();

        try {
            String sql = "SELECT * FROM users WHERE email = ?;";
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, email);

            ResultSet rs = pstm.executeQuery();
            return rs.next();
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Service Usuario: " + error.getMessage());
        }

        return false;

    }

    public void createNewUser(UserDTO obj_user) {
        conn = new ConnectionMysql().connectDB();

        try {
            String insert = "INSERT INTO grupo5_willian.users (name, password, email, cpf) VALUES(?, ?, ?, ?);";
            pstm = conn.prepareStatement(insert);
            pstm.setString(1, obj_user.getName());
            pstm.setString(2, obj_user.getPassword());
            pstm.setString(3, obj_user.getEmail());
            pstm.setString(4, obj_user.getCpf());
            
            pstm.execute();
            pstm.close();
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Service Usuario: " + error.getMessage());
        }
    }
}
