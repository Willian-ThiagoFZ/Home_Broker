/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package views;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import models.AccountDTO;
import models.BrokerDTO;
import models.UserDTO;
import services.AccountService;
import services.BrokerService;
import services.SessionsService;
import views.Dialogs.account.DeleteAccountDialogView;
import views.Dialogs.account.UpdateBalanceAccountDialogView;

/**
 *
 * @author SUPORTE
 */
public class AccountControlView extends javax.swing.JFrame {

    /**
     * Creates new form AccountControlView
     */
    public AccountControlView() {
        initComponents();
        this.session_user = new UserDTO();
        validSession();
        loadBrokersToBox();
        loadAccounts();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        table_accounts = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        input_balance = new javax.swing.JTextField();
        box_type = new javax.swing.JComboBox<>();
        box_broker = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        table_accounts.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "ID", "Corretora", "Número da Conta", "TIPO", "Saldo"
            }
        ));
        jScrollPane1.setViewportView(table_accounts);

        jLabel1.setFont(new java.awt.Font("Fira Code", 1, 18)); // NOI18N
        jLabel1.setText("Controle de Contas de Investimentos ");

        jLabel2.setText("Escolha a Corretora:");

        jLabel3.setText("Tipo da Conta:");

        jLabel4.setText("Saldo:");

        input_balance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                input_balanceActionPerformed(evt);
            }
        });

        box_type.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "Conta Real", "Conta Simulada" }));
        box_type.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                box_typeActionPerformed(evt);
            }
        });

        box_broker.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione" }));

        jButton1.setText("Cadastrar Conta");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel5.setText("Minhas Contas");

        jButton2.setText("Editar Saldo");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Deletar Conta");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Voltar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 47, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(box_broker, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(77, 77, 77)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(box_type, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(78, 78, 78)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                                    .addComponent(jLabel4)
                                    .addComponent(input_balance, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(90, 90, 90)
                                .addComponent(jLabel1)))
                        .addGap(63, 63, 63))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jButton4))
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(input_balance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(box_type, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(box_broker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 113, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void input_balanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_input_balanceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_input_balanceActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (box_broker.getSelectedIndex() != 0 && box_type.getSelectedIndex() != 0){
            try {
                AccountService service = new AccountService();
                int id_cooretora = id_brokers.get(box_broker.getSelectedIndex() - 1);
                String tipo_conta = box_type.getSelectedItem().toString();
                int account_number = new Random().nextInt(900000) + 100000;
                
                AccountDTO account = new AccountDTO();
                account.setUser_id(session_user.getId());
                account.setBroker_id(id_cooretora);
                account.setNumber_account(Integer.toString(account_number));
                account.setType_account(tipo_conta);
                if (tipo_conta == "Conta Real"){
                    account.setBalance(0.0);
                }else{
                    String balance = input_balance.getText();
                    account.setBalance(Double.parseDouble(balance));
                }
                
                service.createNewAccount(account);
                JOptionPane.showMessageDialog(null, "Cadastrado com Sucesso");
                input_balance.setText("");
                box_broker.setSelectedIndex(0);
                box_type.setSelectedIndex(0);
                loadAccounts();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao Criar uma Conta: " + ex);
            }
        }else{
            JOptionPane.showMessageDialog(null, "Preencha Todos os Campos Corretamente!");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void box_typeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_box_typeActionPerformed
        if (box_type.getSelectedItem().equals("Conta Real")){
            input_balance.setText("");
            input_balance.setEnabled(false);
        }else if (box_type.getSelectedItem().equals("Conta Simulada")){
            input_balance.setText("100000.00");
            input_balance.setEnabled(true);
        }else{
            input_balance.setText("");
            input_balance.setEnabled(true);
        }
    }//GEN-LAST:event_box_typeActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        AccountDTO account_delete = getDataRowSelected();
        if (account_delete.getType_account().equals("Conta Real") && account_delete.getBalance() > 0.0){
            JOptionPane.showMessageDialog(null, "Você ainda tem Saldo nesta Conta, antes de deletar saque o seu dinheiro");
        }else{
            new DeleteAccountDialogView(null, true, account_delete).show();
            loadAccounts();
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        AccountDTO account_update = getDataRowSelected();
        if (!account_update.getType_account().equals("Conta Real")){
            new UpdateBalanceAccountDialogView(null, true, account_update).show();
            loadAccounts();
        }else{
            JOptionPane.showMessageDialog(null, "Apenas Contas Simuladas podem ser Alteradas");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        DashboardView page = new  DashboardView();
        page.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AccountControlView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AccountControlView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AccountControlView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AccountControlView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AccountControlView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> box_broker;
    private javax.swing.JComboBox<String> box_type;
    private javax.swing.JTextField input_balance;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table_accounts;
    // End of variables declaration//GEN-END:variables
    
    private final UserDTO session_user;
    Vector<Integer> id_brokers = new Vector<Integer>();
    Map<Integer, String> map_brokers = new HashMap<Integer, String>();
    
    private void validSession() {
        SessionsService functions = new SessionsService();
        try {
            ResultSet result = functions.getActualSession(true);
            if (!result.next()){
                LoginFormView page = new LoginFormView();
                page.setVisible(true);
                dispose();
            }else{
                session_user.setName(result.getString("name"));
                session_user.setId(result.getInt("id"));
                session_user.setCpf(result.getString("cpf"));
                session_user.setEmail(result.getString("email"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Dashboard Init: " + ex);
        }
    }
    
    private AccountDTO getDataRowSelected(){
        AccountDTO account = new AccountDTO();
        try{
            int index_row = table_accounts.getSelectedRow();
            int id_selected = Integer.parseInt(table_accounts.getModel().getValueAt(index_row, 0).toString());
            account.setId(id_selected);
            account.setNumber_account(table_accounts.getModel().getValueAt(index_row, 2).toString());
            account.setType_account(table_accounts.getModel().getValueAt(index_row, 3).toString());
            account.setBalance((double) table_accounts.getModel().getValueAt(index_row, 4));
        } catch(Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Carregar os Dados: " + ex);
        }
        
        return account;
    }

    private void loadBrokersToBox() {
        BrokerService service = new BrokerService();
        try {
            ArrayList<BrokerDTO> broker_list = service.getAllBrokers();
            for (int i = 0; i < broker_list.size(); i++ ){
                int id_broker_get = broker_list.get(i).getId();
                String name_broker = broker_list.get(i).getName();
                id_brokers.addElement(id_broker_get);
                box_broker.addItem(name_broker);
                map_brokers.put(id_broker_get, name_broker);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro Ao Listar Brokers: " + ex);
        }
    }
    
    private void loadAccounts() {
        AccountService service = new AccountService();
        try {
            DefaultTableModel model = (DefaultTableModel) table_accounts.getModel();
            model.setNumRows(0);
            ArrayList<AccountDTO> account_list = service.getAllAccounts(session_user.getId());

            for (int row = 0; row < account_list.size(); row++) {
                String name_broker = map_brokers.get(account_list.get(row).getBroker_id());
                model.addRow(new Object[]{
                    account_list.get(row).getId(),
                    name_broker,
                    account_list.get(row).getNumber_account(),
                    account_list.get(row).getType_account(),
                    account_list.get(row).getBalance(),
                });
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro Ao Listar Contas: " + ex);
        }
    }

}