/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hsms_project;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Asus
 */
public class Blood extends javax.swing.JFrame {
    Connection connection;
    /**
     * Creates new form Blood
     */
    public Blood() {
        initComponents();
        jPopupMenu1.add(jPanel6);
    }
    public ArrayList<BloodClass> bloodtypeList(){
        ArrayList<BloodClass> bloodtypeList= new ArrayList<>();
        
        ResultSet resultset;
        try{
            connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=HSMS_Project;selectMethod=cursor", "sa", "123456");
            Statement statement = connection.createStatement();
            
            String bloodtype, query;
            
            if(jComboBox1.getSelectedIndex() == 0){
                bloodtype = "A+";
            }else if(jComboBox1.getSelectedIndex() == 1){
                bloodtype = "A-";
            }else if(jComboBox1.getSelectedIndex() == 2){
                bloodtype = "B+";
            }else if(jComboBox1.getSelectedIndex() == 3){
                bloodtype = "B-";
            }else if(jComboBox1.getSelectedIndex() == 4){
                bloodtype = "AB+";
            }else if(jComboBox1.getSelectedIndex() == 5){
                bloodtype = "AB-";
            }else if(jComboBox1.getSelectedIndex() == 6){
                bloodtype = "O+";
            }else{
                bloodtype = "O-";
            }
            
            query = "Select BG, Name, Phone1, Phone2, Email, F_Num from SOCIETY_MEMBER where BG = '"+bloodtype+"'";
            resultset = statement.executeQuery(query);
            BloodClass bloodtypeclass;
            while(resultset.next()){
                bloodtypeclass = new BloodClass(resultset.getString("BG"),resultset.getString("Name"),resultset.getString("Phone1"), resultset.getString("Phone2"), resultset.getString("Email"), resultset.getString("F_Num"));
                bloodtypeList.add(bloodtypeclass);

            }    
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return bloodtypeList;
    }
    public ArrayList<BloodClass> bnamelList(){
        ArrayList<BloodClass> bnamelList= new ArrayList<>();
        String query;
        ResultSet resultset;
        try{
            connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=HSMS_Project;selectMethod=cursor", "sa", "123456");
            Statement statement = connection.createStatement();
            
            String name_string = jTextField4.getText();//.toUpperCase();
            query = "Select BG, Name, Phone1, Phone2, Email, F_Num from SOCIETY_MEMBER where Name LIKE '%"+name_string+"%'";
            resultset = statement.executeQuery(query);
            BloodClass bnameclass;
            while(resultset.next()){
                bnameclass = new BloodClass(resultset.getString("BG"),resultset.getString("Name"),resultset.getString("Phone1"), resultset.getString("Phone2"), resultset.getString("Email"), resultset.getString("F_Num"));
                bnamelList.add(bnameclass);

            }
            
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return bnamelList;
    }
    
    public void show_bloodtype_List(){
        ArrayList<BloodClass> bt = bloodtypeList();
        DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
        model.setRowCount(0);
        Object[] row = new Object[6];
        for(int i=0; i<bt.size(); i++){
            row[0] = bt.get(i).getBG();
            row[1] = bt.get(i).getName();
            row[2] = bt.get(i).getPhone1();
            row[3] = bt.get(i).getPhone2();
            row[4] = bt.get(i).getEmail();
            row[5] = bt.get(i).getAddress();
            model.addRow(row);
            
        }
    }
    
    public void show_bloodname_List(){
        ArrayList<BloodClass> bt = bnamelList();
        DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
        model.setRowCount(0);
        Object[] row = new Object[6];
        for(int i=0; i<bt.size(); i++){
            row[0] = bt.get(i).getBG();
            row[1] = bt.get(i).getName();
            row[2] = bt.get(i).getPhone1();
            row[3] = bt.get(i).getPhone2();
            row[4] = bt.get(i).getEmail();
            row[5] = bt.get(i).getAddress();
            model.addRow(row);
            
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel6 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        jList1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jScrollPane6.setViewportView(jList1);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
        );

        jPopupMenu1.setFocusable(false);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Society Blood Desk");

        jTable1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "BG", "Name", "Phone1", "Phone2", "Email", "Address"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setText("Blood Group");

        jComboBox1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-", " " }));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("Name");

        jTextField4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField4KeyReleased(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon("E:\\3.1\\Database Lab\\HSMS_Project\\test\\search_icon.png")); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon("E:\\3.1\\Database Lab\\HSMS_Project\\test\\search_icon.png")); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton1.setText("Main Menu");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1576, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)
                        .addGap(120, 120, 120)
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addGap(307, 307, 307)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton3)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 670, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        show_bloodtype_List();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        show_bloodname_List();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        gotoMainMenu();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyReleased
        // TODO add your handling code here:
        DefaultListModel model = new DefaultListModel();
            jList1.setModel(model);    
            try{
                connection = DriverManager.getConnection(
                        "jdbc:sqlserver://localhost:1433;databaseName=HSMS_Project;selectMethod=cursor", "sa", "123456");
                Statement statement = connection.createStatement();
                //ResultSet resultSet = statement.executeQuery("select SOCIETY_MEMBER.Name from SOCIETY_MEMBER"); 

                String search = jTextField4.getText().trim();

                model.removeAllElements();
                if(!search.equals("")){
                    ResultSet resultSet = statement.executeQuery("select Name from SOCIETY_MEMBER where Name like '%"+search+"%'"); 
                    while(resultSet.next()){
                        String name = resultSet.getString("Name");
                        model.addElement(name);
                    }
                }


            }catch(Exception e){
                e.printStackTrace();
            }

            jPopupMenu1.show(jTextField4, 0, jTextField4.getHeight());
            jList1.addMouseListener(new MouseAdapter() {
             public void mouseClicked(MouseEvent me) {
                jPopupMenu1.setVisible(false);
                if (me.getClickCount() == 1) {
                   JList target = (JList)me.getSource();
                   int index = target.locationToIndex(me.getPoint());
                   if (index >= 0) {
                      Object item = target.getModel().getElementAt(index);
                      jTextField4.setText(item.toString());
                   }
                }
             }
          });
    }//GEN-LAST:event_jTextField4KeyReleased
     
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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Blood.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Blood.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Blood.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Blood.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Blood().setVisible(true);
            }
        });
    }
    public void gotoMainMenu(){
        OpeningDisplay open = new OpeningDisplay();
        open.setVisible(true);
        this.setVisible(false);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField4;
    // End of variables declaration//GEN-END:variables
}
