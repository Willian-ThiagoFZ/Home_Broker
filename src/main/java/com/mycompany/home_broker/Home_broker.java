/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.home_broker;

/*import database.ConnectionMysql;
import java.sql.Connection;
import java.sql.ResultSet;*/
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import services.StockService;

public class Home_broker {

    public static void main(String[] args) throws SQLException {
        
        StockService service = new StockService();
        try {
            service.find_stocks("AAPL,TSLA,GOGL,AMZN,MSFT,IBM");
            /*ConnectionMysql connectClass = new ConnectionMysql();
            Connection conn = connectClass.connectDB();
            try {
            ResultSet result = conn.createStatement().executeQuery("SELECT * FROM users");
            while (result.next()){
            System.out.println("nome:" + result.getString("name"));
            }
            } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            } finally {
            if (conn != null){
            conn.close();
            }
            }*/
        } catch (IOException ex) {
            System.err.println(ex);
        }
        
    }
}
