package services;

import database.ConnectionMysql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;

public class SessionsService {
    
    Connection conn;
    PreparedStatement pstm;
    
    public void createSession(int user_id){
        conn = new ConnectionMysql().connectDB();
        
        try {
            String insert = "INSERT INTO grupo5_willian.sessions (create_at, expire_at, user_id) VALUES(?, ?, ?);";
            pstm = conn.prepareStatement(insert);
            Date today = new Date();
            Date tomorrow;
            Calendar c = Calendar.getInstance(); 
            c.setTime(today); 
            c.add(Calendar.DATE, 1);
            tomorrow = c.getTime();
            pstm.setString(1, formatDataForDB(today));
            pstm.setString(2, formatDataForDB(tomorrow));
            pstm.setInt(3, user_id);
            
            pstm.execute();
            pstm.close();
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Service Create Session: " + error.getMessage());
        }
    }
    
    public ResultSet getActualSession(boolean activateAllValidExpires) throws SQLException{
        conn = new ConnectionMysql().connectDB();
        
        try {
            if (activateAllValidExpires){
                ExpireSessions(conn);
            }
            
            String select = """
                select
                    u.id,
                    u.name,
                    u.cpf,
                    u.email
                from
                    grupo5_willian.sessions s2
                join grupo5_willian.users u on
                    u.id = s2.user_id
                where
                    s2.id = (
                    select
                        max(id)
                    from
                        grupo5_willian.sessions s
                    where
                        active = 1);
            """;
            pstm = conn.prepareStatement(select);
            ResultSet rs = pstm.executeQuery();
            return rs;
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Service Create Session: " + error.getMessage());
        }
        
        return null;
    }
    
    public void ExpireSessions(Connection conn) throws SQLException{
        try {
            String select = """
                SELECT
                    s.id
                FROM
                    grupo5_willian.SESSIONS S
                WHERE
                    ACTIVE = 1 and 
                    expire_at <= CURDATE()
            """;
            pstm = conn.prepareStatement(select);
            ResultSet rs = pstm.executeQuery();
            
            while (rs.next()) {
                String id_session = rs.getString("id");
                String update = "UPDATE grupo5_willian.SESSIONS SET active = 0 WHERE id = ?";
                pstm = conn.prepareStatement(update);
                pstm.setString(1, id_session);
                pstm.execute();
            }
            
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Service Create Session: " + error.getMessage());
        }finally{
            pstm.close();
        }
    }
    
    public void LogoutSessions() throws SQLException{
        conn = new ConnectionMysql().connectDB();
        
        try {
            String query = "UPDATE grupo5_willian.SESSIONS SET active = 0 WHERE active = 1;";
            pstm = conn.prepareStatement(query);
            pstm.execute();
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Service Create Session: " + error.getMessage());
        }finally{
            pstm.close();
        }
    }
    
    public String formatDataForDB(Date data){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String newDateString = sdf.format(data);
        return newDateString;
    }
    
}
