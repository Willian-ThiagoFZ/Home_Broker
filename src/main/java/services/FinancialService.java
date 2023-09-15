package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import models.AccountDTO;
import models.MovementAccountDTO;

public class FinancialService {
    
    Connection conn;
    PreparedStatement pstm;
    ResultSet rs;
    //ArrayList<AccountDTO> response_accounts = new ArrayList<>();
    
    public void createNewMovement(MovementAccountDTO movement){
        
    }
    
}
