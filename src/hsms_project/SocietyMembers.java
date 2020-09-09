
package hsms_project;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PrinterException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
/**
 *
 * @author Personal-PC
 */
public class SocietyMembers extends javax.swing.JFrame {

    /**
     * Creates new form RegForm
     */
    public SocietyMembers() {
        this.setTitle("Society Member");
        initComponents();
        
        
        //Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        //this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        
        jPopupMenu1.add(jPanel6);
        //soc_table1.setEnabled(false);
    }
    
      public ArrayList<UserInfo> userList(){
        ArrayList<UserInfo> usersList = new ArrayList<>();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection  connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=HSMS_Project;selectMethod=cursor", "sa", "123456");
            Statement statement = connection.createStatement();
            String query1 = "SELECT * from SOCIETY_MEMBER where State=1";
            ResultSet rs = statement.executeQuery(query1);
            UserInfo user;
            while(rs.next()){
                user = new UserInfo(rs.getInt("SMI"), rs.getString("Name"), rs.getString("Phone1"), rs.getString("Phone2"), rs.getString("Email"), rs.getString("DOB"), rs.getString("BG"),  rs.getString("NID"), rs.getString("F_Num"),  rs.getString("P_Add"), rs.getString("Job"), rs.getString("S_Name"), rs.getString("S_Phone") , rs.getString("S_Email"));
                usersList.add(user);
                
            }
            
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
             
        }
        
        return usersList;
    }
      public void show_user(){
        ArrayList<UserInfo> list = userList();
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        model.setRowCount(0);
        Object[] column = new Object[14];
        for(int i=0; i<list.size(); i++){
            column[0]= list.get(i).getSMI();
            column[1]= list.get(i).getName();
            column[2]= list.get(i).getPhone1();
            column[3]= list.get(i).getPhone2();
            
            column[4]= list.get(i).getEmail();
            column[5]= list.get(i).getDOB();
            column[6]= list.get(i).getBG();
      
            column[7]= list.get(i).getNID();
            //column[8]= list.get(i).getF_Num();
            column[8]= list.get(i).getP_Add();
            column[9]= list.get(i).getOccupation();
            
            column[10]= list.get(i).getSub_Name();
            column[11]= list.get(i).getSub_Phone();
            column[12]= list.get(i).getSub_Email();
            model.addRow(column);
        }
     }
      
      public ArrayList<UserInfo> userListSMI(){
        ArrayList<UserInfo> usersList = new ArrayList<>();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection  connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=HSMS_Project;selectMethod=cursor", "sa", "123456");
            Statement statement = connection.createStatement();
            String smi = smiSearch1.getText().toString();
            String query = "SELECT * from SOCIETY_MEMBER where SMI = "+smi+"";
            ResultSet rs = statement.executeQuery(query);
            UserInfo user;
            while(rs.next()){
                user = new UserInfo(rs.getInt("SMI"), rs.getString("Name"), rs.getString("Phone1"), rs.getString("Phone2"), rs.getString("Email"), rs.getString("DOB"), rs.getString("BG"),  rs.getString("NID"), rs.getString("F_Num"),  rs.getString("P_Add"), rs.getString("Job"), rs.getString("S_Name"), rs.getString("S_Phone") , rs.getString("S_Email"));
                usersList.add(user);
                
            }
            
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
             
        }
        
        return usersList;
    }
      public void show_user_SMI(){
        ArrayList<UserInfo> list = userListSMI();
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        model.setRowCount(0);
        Object[] column = new Object[14];
        for(int i=0; i<list.size(); i++){
            column[0]= list.get(i).getSMI();
            column[1]= list.get(i).getName();
            column[2]= list.get(i).getPhone1();
            column[3]= list.get(i).getPhone2();
            
            column[4]= list.get(i).getEmail();
            column[5]= list.get(i).getDOB();
            column[6]= list.get(i).getBG();
      
            column[7]= list.get(i).getNID();
            column[8]= list.get(i).getF_Num();
            column[9]= list.get(i).getP_Add();
            column[10]= list.get(i).getOccupation();
            
            column[11]= list.get(i).getSub_Name();
            column[12]= list.get(i).getSub_Phone();
            column[13]= list.get(i).getSub_Email();
            model.addRow(column);
        }
     }
      
      public ArrayList<UserInfo> userListNAME(){
        ArrayList<UserInfo> usersList = new ArrayList<>();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection  connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=HSMS_Project;selectMethod=cursor", "sa", "123456");
            Statement statement = connection.createStatement();
            String name = nameSearch1.getText().toString();
            String query1 = "SELECT * from SOCIETY_MEMBER where Name like '%"+name+"%'";
            ResultSet rs = statement.executeQuery(query1);
            UserInfo user;
            while(rs.next()){
                user = new UserInfo(rs.getInt("SMI"), rs.getString("Name"), rs.getString("Phone1"), rs.getString("Phone2"), rs.getString("Email"), rs.getString("DOB"), rs.getString("BG"),  rs.getString("NID"), rs.getString("F_Num"),  rs.getString("P_Add"), rs.getString("Job"), rs.getString("S_Name"), rs.getString("S_Phone") , rs.getString("S_Email"));
                usersList.add(user);
                
            }
            
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
             
        }
        
        return usersList;
    }
      public void show_user_NAME(){
        ArrayList<UserInfo> list = userListNAME();
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        model.setRowCount(0);
        Object[] column = new Object[14];
        for(int i=0; i<list.size(); i++){
            column[0]= list.get(i).getSMI();
            column[1]= list.get(i).getName();
            column[2]= list.get(i).getPhone1();
            column[3]= list.get(i).getPhone2();
            
            column[4]= list.get(i).getEmail();
            column[5]= list.get(i).getDOB();
            column[6]= list.get(i).getBG();
      
            column[7]= list.get(i).getNID();
            column[8]= list.get(i).getF_Num();
            column[9]= list.get(i).getP_Add();
            column[10]= list.get(i).getOccupation();
            
            column[11]= list.get(i).getSub_Name();
            column[12]= list.get(i).getSub_Phone();
            column[13]= list.get(i).getSub_Email();
            model.addRow(column);
        }
     }
      
       
       
       
       
      public void erase(){
         name.setText("");
        phone1.setText("");
        phone2.setText("");
        email.setText("");
         dob.setCalendar(null);
        bg.setSelectedIndex(0);
        nid.setText("");
        fnum.setText("");
        padd.setText("");
        occupation.setText("");
        sname.setText("");
        sphone.setText("");
        semail.setText("");
    }
      
      public void erase1(){
        name_e.setText("");
        phone1_e.setText("");
        phone2_e.setText("");
        email_e.setText("");
        padd_e.setText("");
        occupation_e.setText("");
        sname_e.setText("");
        sphone_e.setText("");
        semail_e.setText("");
        smiSearch.setText("");
        nameSearch2.setText("");
    }
    
 public ArrayList<FlatClass> FlatList(){
        ArrayList<FlatClass> flatList= new ArrayList<>();
        try{
            Connection connection;
            connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=HSMS_Project;selectMethod=cursor", "sa", "123456");
            Statement statement = connection.createStatement();
            
            //int month = Integer.valueOf(monthString);
            String smi = jTextField8.getText().toString();
           
            
            String query;
            query = "SELECT FLAT.F_Num, SOCIETY_MEMBER.Name, SOCIETY_MEMBER.Phone1, SOCIETY_MEMBER.SMI \n" +
                    "FROM FLAT INNER JOIN SOCIETY_MEMBER ON SOCIETY_MEMBER.SMI = FLAT.SMI where FLAT.SMI = "+smi+"";

            ResultSet resultset = statement.executeQuery(query);
            FlatClass flatclass;
            while(resultset.next()){
                flatclass = new FlatClass(resultset.getString("F_Num"), resultset.getString("Name"), resultset.getString("Phone1"), resultset.getInt("SMI"));
                flatList.add(flatclass);
                
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return flatList;
    }   
    public void show_flat_list_SMI(){
        ArrayList<FlatClass> flat = FlatList(); 
        DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
        model.setRowCount(0);
        Object[] row = new Object[4];
        for(int i=0; i<flat.size(); i++){
            row[0] = flat.get(i).getFnum();
            row[1] = flat.get(i).getName();
            row[2] = flat.get(i).getContact();
            row[3] = flat.get(i).getSMI();
            model.addRow(row);
            
        }
    }
    
    public ArrayList<FlatClass> FlatList_name(){
        ArrayList<FlatClass> flatList= new ArrayList<>();
        try{
            Connection connection;
            connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=HSMS_Project;selectMethod=cursor", "sa", "123456");
            Statement statement = connection.createStatement();
            
            String name = jTextField11.getText().toString();
            
            String query;
            query = "SELECT FLAT.F_Num, SOCIETY_MEMBER.Name, SOCIETY_MEMBER.Phone1, SOCIETY_MEMBER.SMI \n" +
                    "FROM FLAT INNER JOIN SOCIETY_MEMBER ON SOCIETY_MEMBER.SMI = FLAT.SMI where SOCIETY_MEMBER.Name like '%"+name+"%'";

            ResultSet resultset = statement.executeQuery(query);
            FlatClass flatclass;
            while(resultset.next()){
                flatclass = new FlatClass(resultset.getString("F_Num"), resultset.getString("Name"), resultset.getString("Phone1"), resultset.getInt("SMI"));
                flatList.add(flatclass);
                
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return flatList;
    }   
    public void show_flat_list_NAME(){
        ArrayList<FlatClass> flat = FlatList_name(); 
        DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
        model.setRowCount(0);
        Object[] row = new Object[4];
        for(int i=0; i<flat.size(); i++){
            row[0] = flat.get(i).getFnum();
            row[1] = flat.get(i).getName();
            row[2] = flat.get(i).getContact();
            row[3] = flat.get(i).getSMI();
            model.addRow(row);
            
        }
    }
    
    
    
    public ArrayList<FlatClass> FlatList_flat(){
        ArrayList<FlatClass> flatList= new ArrayList<>();
        try{
            Connection connection;
            connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=HSMS_Project;selectMethod=cursor", "sa", "123456");
            Statement statement = connection.createStatement();
            
            //int month = Integer.valueOf(monthString);
            String num = jTextField10.getText().toString();
            //String max = Integer.valueOf(jTextField5.getText());
            //String minString = String.valueOf(min);
            //String maxString = String.valueOf(max);
            
            String query;
            query = "SELECT FLAT.F_Num, SOCIETY_MEMBER.Name, SOCIETY_MEMBER.Phone1, SOCIETY_MEMBER.SMI \n" +
                    "FROM FLAT INNER JOIN SOCIETY_MEMBER ON SOCIETY_MEMBER.SMI = FLAT.SMI where FLAT.F_Num like '%"+num+"%'";

            ResultSet resultset = statement.executeQuery(query);
            FlatClass flatclass;
            while(resultset.next()){
                flatclass = new FlatClass(resultset.getString("F_Num"), resultset.getString("Name"), resultset.getString("Phone1"), resultset.getInt("SMI"));
                flatList.add(flatclass);
                
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return flatList;
    }   
    public void show_flat_list_FLAT(){
        ArrayList<FlatClass> flat = FlatList_flat(); 
        DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
        model.setRowCount(0);
        Object[] row = new Object[4];
        for(int i=0; i<flat.size(); i++){
            row[0] = flat.get(i).getFnum();
            row[1] = flat.get(i).getName();
            row[2] = flat.get(i).getContact();
            row[3] = flat.get(i).getSMI();
            model.addRow(row);
            
        }
    }
    
    
    
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel6 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        Reg_P = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        name = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        phone1 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        phone2 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        email = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        dob = new com.toedter.calendar.JDateChooser();
        bg = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        nid = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        fnum = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        padd = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        occupation = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        sname = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        sphone = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        semail = new javax.swing.JTextField();
        save = new javax.swing.JButton();
        reset = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        printSocFeeBtn = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jMonthChooser1 = new com.toedter.calendar.JMonthChooser();
        jYearChooser1 = new com.toedter.calendar.JYearChooser();
        jLabel1 = new javax.swing.JLabel();
        jButton15 = new javax.swing.JButton();
        MT_P = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        nameSearch1 = new javax.swing.JTextField();
        searchBtn3 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        delete = new javax.swing.JButton();
        smiSearch1 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        Search_P = new javax.swing.JPanel();
        padd_e = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        phone1_e = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        sname_e = new javax.swing.JTextField();
        sphone_e = new javax.swing.JTextField();
        email_e = new javax.swing.JTextField();
        phone2_e = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        semail_e = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        occupation_e = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        name_e = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        searchBtn1 = new javax.swing.JButton();
        update = new javax.swing.JButton();
        clear = new javax.swing.JButton();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        nameSearch2 = new javax.swing.JTextField();
        searchBtn4 = new javax.swing.JButton();
        smiSearch = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTextField8 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        former_table = new javax.swing.JTable();
        showBtnFormer = new javax.swing.JButton();
        hide_former = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();

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
        setTitle("Society Members");
        setResizable(false);

        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        Reg_P.setPreferredSize(new java.awt.Dimension(1600, 800));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Name");

        name.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Mobile(P)");

        phone1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        phone1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                phone1ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("Mobile(O)");

        phone2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        phone2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                phone2ActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("Email");

        email.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setText("Date Of Birth");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setText("Blood Group");

        dob.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        bg.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        bg.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Null", "A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-" }));
        bg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bgActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setText("National I.D.");

        nid.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        nid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nidActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setText("Flat Number");

        fnum.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        fnum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fnumActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel11.setText("Permanent Address");

        padd.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel12.setText("Occupation");

        occupation.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        occupation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                occupationActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel13.setText("Sub. Name");

        sname.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel14.setText("Sub. Phone");

        sphone.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel15.setText("Sub. Email");

        semail.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        save.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        save.setText("Save");
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });

        reset.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        reset.setText("Reset");
        reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton3.setText("Main Menu");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        jTextArea1.setRows(5);
        jScrollPane4.setViewportView(jTextArea1);

        printSocFeeBtn.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        printSocFeeBtn.setText("Print");
        printSocFeeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printSocFeeBtnActionPerformed(evt);
            }
        });

        jButton9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton9.setText("Generate Card");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jMonthChooser1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jYearChooser1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Membership Starting From");

        jButton15.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton15.setText("Clear");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Reg_PLayout = new javax.swing.GroupLayout(Reg_P);
        Reg_P.setLayout(Reg_PLayout);
        Reg_PLayout.setHorizontalGroup(
            Reg_PLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Reg_PLayout.createSequentialGroup()
                .addGroup(Reg_PLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Reg_PLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(save, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(reset)
                        .addGap(163, 163, 163)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addComponent(printSocFeeBtn)
                        .addGap(43, 43, 43)
                        .addComponent(jButton15)
                        .addGap(177, 177, 177))
                    .addGroup(Reg_PLayout.createSequentialGroup()
                        .addGap(347, 347, 347)
                        .addGroup(Reg_PLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(Reg_PLayout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(phone1, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(Reg_PLayout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(phone2, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(Reg_PLayout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(Reg_PLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(Reg_PLayout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(bg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(203, 203, 203))
                            .addGroup(Reg_PLayout.createSequentialGroup()
                                .addGroup(Reg_PLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel1))
                                .addGroup(Reg_PLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(Reg_PLayout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addGroup(Reg_PLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(nid, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(padd, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(Reg_PLayout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addGroup(Reg_PLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(sname, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(occupation, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(sphone, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(semail, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(Reg_PLayout.createSequentialGroup()
                                                .addComponent(jMonthChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jYearChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                            .addGroup(Reg_PLayout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(18, 18, 18)
                                .addComponent(fnum, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2))
                            .addGroup(Reg_PLayout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addComponent(dob, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(118, 118, 118)))
                .addComponent(jButton3)
                .addContainerGap())
        );
        Reg_PLayout.setVerticalGroup(
            Reg_PLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Reg_PLayout.createSequentialGroup()
                .addGroup(Reg_PLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3)
                    .addGroup(Reg_PLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(Reg_PLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(Reg_PLayout.createSequentialGroup()
                                .addGroup(Reg_PLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(Reg_PLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(phone1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4))
                                .addGap(18, 18, 18)
                                .addGroup(Reg_PLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(phone2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5))
                                .addGap(18, 18, 18)
                                .addGroup(Reg_PLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6))
                                .addGap(18, 18, 18)
                                .addGroup(Reg_PLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                                    .addComponent(dob, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(Reg_PLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(bg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8))
                                .addGap(18, 18, 18)
                                .addGroup(Reg_PLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(nid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9))
                                .addGap(18, 18, 18)
                                .addGroup(Reg_PLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(fnum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10))
                                .addGap(18, 18, 18)
                                .addGroup(Reg_PLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(padd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11))
                                .addGap(18, 18, 18)
                                .addGroup(Reg_PLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(occupation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12))
                                .addGap(18, 18, 18)
                                .addGroup(Reg_PLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(sname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel13))
                                .addGap(18, 18, 18)
                                .addGroup(Reg_PLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(sphone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel14))
                                .addGap(18, 18, 18)
                                .addGroup(Reg_PLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(semail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel15))
                                .addGroup(Reg_PLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(Reg_PLayout.createSequentialGroup()
                                        .addGap(13, 13, 13)
                                        .addComponent(jYearChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(Reg_PLayout.createSequentialGroup()
                                        .addGap(15, 15, 15)
                                        .addGroup(Reg_PLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jMonthChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(Reg_PLayout.createSequentialGroup()
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 633, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)))
                        .addGap(26, 26, 26)
                        .addGroup(Reg_PLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(reset)
                            .addComponent(save)
                            .addComponent(printSocFeeBtn)
                            .addComponent(jButton9)
                            .addComponent(jButton15))))
                .addContainerGap(45, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Registration", Reg_P);

        MT_P.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        MT_P.setPreferredSize(new java.awt.Dimension(1535, 800));

        jLabel31.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel31.setText("Enter SMI");

        jLabel32.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel32.setText("Enter Name");

        nameSearch1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        nameSearch1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                nameSearch1KeyReleased(evt);
            }
        });

        searchBtn3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        searchBtn3.setText("Search");
        searchBtn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBtn3ActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton1.setText("Search");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        delete.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        delete.setText("Delete");
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });

        smiSearch1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        smiSearch1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                smiSearch1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton2.setText("Show All Members");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton4.setText("Main Menu");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "SMI", "Name", "Phone-1", "Phone-2", "Email", "DOB", "BG", "NID", "P_Address", "Occupation", "S_Name", "S_Phone", "S_Email"
            }
        ));
        jScrollPane1.setViewportView(jTable2);

        javax.swing.GroupLayout MT_PLayout = new javax.swing.GroupLayout(MT_P);
        MT_P.setLayout(MT_PLayout);
        MT_PLayout.setHorizontalGroup(
            MT_PLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MT_PLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(MT_PLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(MT_PLayout.createSequentialGroup()
                        .addGap(0, 67, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addGap(85, 85, 85)
                        .addComponent(jLabel31)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(smiSearch1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addGap(92, 92, 92)
                        .addComponent(jLabel32)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nameSearch1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchBtn3, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(99, 99, 99)
                        .addComponent(delete, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(122, 122, 122)
                        .addComponent(jButton4)))
                .addContainerGap())
        );
        MT_PLayout.setVerticalGroup(
            MT_PLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MT_PLayout.createSequentialGroup()
                .addGroup(MT_PLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(MT_PLayout.createSequentialGroup()
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(MT_PLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(MT_PLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel31)
                            .addComponent(nameSearch1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(searchBtn3)
                            .addComponent(jLabel32)
                            .addComponent(jButton1)
                            .addComponent(delete)
                            .addComponent(smiSearch1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2))
                        .addGap(35, 35, 35)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 670, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
        );

        jTabbedPane1.addTab("Search", MT_P);

        padd_e.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        padd_e.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                padd_eActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel17.setText("Phone 1");

        phone1_e.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        phone1_e.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                phone1_eActionPerformed(evt);
            }
        });

        jLabel28.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel28.setText("Sub. Email");

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel24.setText("Permanent Address");

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel16.setText("Name");

        sname_e.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        sname_e.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sname_eActionPerformed(evt);
            }
        });

        sphone_e.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        sphone_e.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sphone_eActionPerformed(evt);
            }
        });

        email_e.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        email_e.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                email_eActionPerformed(evt);
            }
        });

        phone2_e.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        phone2_e.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                phone2_eActionPerformed(evt);
            }
        });

        jLabel29.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel29.setText("Edit Society Member Information");

        semail_e.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        semail_e.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                semail_eActionPerformed(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel25.setText("Occupation");

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel19.setText("Email");

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel18.setText("Phone 2");

        occupation_e.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        occupation_e.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                occupation_eActionPerformed(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel27.setText("Sub Phone");

        name_e.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        name_e.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                name_eActionPerformed(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel26.setText("Sub. Name");

        searchBtn1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        searchBtn1.setText("Search");
        searchBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBtn1ActionPerformed(evt);
            }
        });

        update.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        update.setText("Update");
        update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionPerformed(evt);
            }
        });

        clear.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        clear.setText("Clear");
        clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearActionPerformed(evt);
            }
        });

        jLabel33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/pencil.jpg"))); // NOI18N

        jLabel34.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel34.setText("Enter SMI");

        jLabel35.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel35.setText("Enter Name");

        nameSearch2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        nameSearch2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                nameSearch2KeyReleased(evt);
            }
        });

        searchBtn4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        searchBtn4.setText("Search");
        searchBtn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBtn4ActionPerformed(evt);
            }
        });

        smiSearch.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        smiSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                smiSearchActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton5.setText("Main Menu");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Search_PLayout = new javax.swing.GroupLayout(Search_P);
        Search_P.setLayout(Search_PLayout);
        Search_PLayout.setHorizontalGroup(
            Search_PLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Search_PLayout.createSequentialGroup()
                .addContainerGap(436, Short.MAX_VALUE)
                .addGroup(Search_PLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Search_PLayout.createSequentialGroup()
                        .addComponent(jLabel29)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel33)
                        .addGap(308, 308, 308)
                        .addComponent(jButton5)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Search_PLayout.createSequentialGroup()
                        .addGroup(Search_PLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Search_PLayout.createSequentialGroup()
                                .addComponent(jLabel34)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(smiSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(searchBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(90, 90, 90)
                                .addComponent(jLabel35)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nameSearch2, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(searchBtn4, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Search_PLayout.createSequentialGroup()
                                .addGroup(Search_PLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(Search_PLayout.createSequentialGroup()
                                        .addGroup(Search_PLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel24)
                                            .addComponent(jLabel16))
                                        .addGap(18, 18, 18)
                                        .addGroup(Search_PLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(name_e, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(padd_e, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(Search_PLayout.createSequentialGroup()
                                        .addComponent(jLabel25)
                                        .addGap(18, 18, 18)
                                        .addComponent(occupation_e, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(Search_PLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(Search_PLayout.createSequentialGroup()
                                            .addComponent(jLabel18)
                                            .addGap(18, 18, 18)
                                            .addComponent(phone2_e, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(Search_PLayout.createSequentialGroup()
                                            .addComponent(jLabel17)
                                            .addGap(18, 18, 18)
                                            .addComponent(phone1_e, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(Search_PLayout.createSequentialGroup()
                                        .addComponent(jLabel19)
                                        .addGap(18, 18, 18)
                                        .addComponent(email_e, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(Search_PLayout.createSequentialGroup()
                                        .addComponent(jLabel26)
                                        .addGap(18, 18, 18)
                                        .addComponent(sname_e, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(Search_PLayout.createSequentialGroup()
                                        .addComponent(jLabel27)
                                        .addGap(18, 18, 18)
                                        .addComponent(sphone_e, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(Search_PLayout.createSequentialGroup()
                                        .addComponent(jLabel28)
                                        .addGap(18, 18, 18)
                                        .addComponent(semail_e, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(208, 208, 208)))
                        .addGap(351, 351, 351))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Search_PLayout.createSequentialGroup()
                        .addComponent(update, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(clear, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(639, 639, 639))))
        );
        Search_PLayout.setVerticalGroup(
            Search_PLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Search_PLayout.createSequentialGroup()
                .addGroup(Search_PLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Search_PLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(Search_PLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                    .addComponent(jButton5))
                .addGap(18, 18, 18)
                .addGroup(Search_PLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(Search_PLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel34)
                        .addComponent(nameSearch2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(searchBtn4)
                        .addComponent(jLabel35)
                        .addComponent(smiSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(searchBtn1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(33, 33, 33)
                .addGroup(Search_PLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(name_e, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(Search_PLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(phone1_e, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addGap(18, 18, 18)
                .addGroup(Search_PLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(phone2_e, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(Search_PLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(email_e, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addGap(18, 18, 18)
                .addGroup(Search_PLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(padd_e, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24))
                .addGap(18, 18, 18)
                .addGroup(Search_PLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(occupation_e, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(Search_PLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sname_e, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26))
                .addGap(18, 18, 18)
                .addGroup(Search_PLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sphone_e, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27))
                .addGap(18, 18, 18)
                .addGroup(Search_PLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(semail_e, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28))
                .addGap(74, 74, 74)
                .addGroup(Search_PLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(clear)
                    .addComponent(update))
                .addContainerGap(124, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Edit Info", Search_P);

        jButton6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton6.setText("Main Menu");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel20.setText("Insert Flat");

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel22.setText("Flat Exchange");

        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jTextField2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jTextField3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jTextField5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jTextField7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jTextField9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jButton7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton7.setText("Add");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton8.setText("Exchange");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jTable1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Flat Number", "Owner's Name", "Contact", "SMI"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        jTextField8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jTextField10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jTextField11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField11.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField11KeyReleased(evt);
            }
        });

        jButton10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton10.setText("Search");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton11.setText("Search");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton12.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton12.setText("Search");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jButton13.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton13.setText("Delete");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jButton14.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton14.setText("Clear");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel21.setText("SMI ");

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel23.setText("Flat's to be exchanged");

        jLabel30.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel30.setText("SMI ");

        jLabel36.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel36.setText("Flat Number");

        jLabel37.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel37.setText("SMI ");

        jLabel38.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel38.setText("Society Member's Name");

        jLabel39.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel39.setText("Flat Number");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(112, 112, 112)
                        .addComponent(jLabel22))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(99, 99, 99)
                        .addComponent(jButton8))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel21)
                                .addGap(63, 63, 63)
                                .addComponent(jLabel23))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(63, 63, 63)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton7)
                                    .addComponent(jLabel20)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(21, 21, 21)
                                        .addComponent(jLabel30)))
                                .addGap(81, 81, 81)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel36)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(79, 79, 79)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 107, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jButton10)
                                            .addComponent(jTextField8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(132, 132, 132))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel37)
                                        .addGap(155, 155, 155)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(88, 88, 88)
                                        .addComponent(jButton11))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGap(20, 20, 20)
                                            .addComponent(jLabel38))))
                                .addGap(164, 164, 164)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(27, 27, 27)
                                        .addComponent(jButton12))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(9, 9, 9)
                                        .addComponent(jLabel39))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton13)
                                .addGap(339, 339, 339)
                                .addComponent(jButton14)
                                .addGap(147, 147, 147)))
                        .addGap(203, 203, 203))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton6)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(jLabel20)
                        .addGap(15, 15, 15)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel30)
                            .addComponent(jLabel36))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jButton7))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel37, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel39, javax.swing.GroupLayout.Alignment.TRAILING))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(jButton12))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                            .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(jButton10))))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel38)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jButton11)))
                            .addGap(38, 38, 38)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton13)
                                .addComponent(jButton14)))
                        .addComponent(jButton6)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21)
                            .addComponent(jLabel23))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jButton8))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(121, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Society Flat", jPanel1);

        former_table.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        former_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "SMI", "Name", "Mobile(P)", "Mobile(O)", "Email", "DOB", "BG", "NID", "Flat Number", "Per. Add.", "Occupation", "Sub Name", "Sub Phone", "Sub Email"
            }
        ));
        jScrollPane3.setViewportView(former_table);

        showBtnFormer.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        showBtnFormer.setText("Show All");
        showBtnFormer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showBtnFormerActionPerformed(evt);
            }
        });

        hide_former.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        hide_former.setText("Hide");
        hide_former.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hide_formerActionPerformed(evt);
            }
        });

        jButton16.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton16.setText("Main Menu");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(614, 614, 614)
                        .addComponent(showBtnFormer)
                        .addGap(100, 100, 100)
                        .addComponent(hide_former)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 582, Short.MAX_VALUE)
                        .addComponent(jButton16)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(showBtnFormer)
                            .addComponent(hide_former)))
                    .addComponent(jButton16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 670, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Former Members", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1600, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 800, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void nidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nidActionPerformed

    private void bgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bgActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bgActionPerformed

    private void phone2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_phone2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_phone2ActionPerformed

    private void phone1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_phone1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_phone1ActionPerformed

    private void nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameActionPerformed

    private void fnumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fnumActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fnumActionPerformed

    private void occupationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_occupationActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_occupationActionPerformed

    private void name_eActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_name_eActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_name_eActionPerformed

    private void phone1_eActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_phone1_eActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_phone1_eActionPerformed

    private void phone2_eActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_phone2_eActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_phone2_eActionPerformed

    private void occupation_eActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_occupation_eActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_occupation_eActionPerformed

    private void email_eActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_email_eActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_email_eActionPerformed

    private void padd_eActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_padd_eActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_padd_eActionPerformed

    private void sname_eActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sname_eActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sname_eActionPerformed

    private void semail_eActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_semail_eActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_semail_eActionPerformed

    private void sphone_eActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sphone_eActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sphone_eActionPerformed

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
         //fetching data from swing and storing it into database
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection  connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=HSMS_Project;selectMethod=cursor", "sa", "123456");
           String query = "insert into SOCIETY_MEMBER(Name, Phone1, Phone2, Email,  DOB, BG, NID, F_Num, P_Add, Job, S_Name, S_Phone, S_Email, StartMont, StartYear, State) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
           java.sql.PreparedStatement ps = connection.prepareStatement(query);
        /*    ps.setString(1, name.getText());*/
            ps.setString(2, phone1.getText());
            ps.setString(3, phone2.getText());
            ps.setString(4, email.getText());
            
           ps.setString(7, nid.getText());
         /*  ps.setString(8, fnum.getText());
           ps.setString(9, padd.getText());
           ps.setString(10, occupation.getText());
           ps.setString(11, sname.getText());
           ps.setString(12, sphone.getText());
           ps.setString(13, semail.getText());
           
           int start_month = jMonthChooser1.getMonth()+1;
           int start_year = jYearChooser1.getYear();
           ps.setString(14, String.valueOf(start_month));
           ps.setString(15, String.valueOf(start_year));*/
        
              //checking if any of these fields are valid or not 
         try{
            connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=HSMS_Project;selectMethod=cursor", "sa", "123456");
            Statement statement = connection.createStatement();
            
            //String name_string = String.valueOf(jComboBox3.getSelectedItem());//.toUpperCase();
            //System.out.println(name_string);
            String name_string = jTextField7.getText().toString();
            query = "Select COUNT(*) as count from SOCIETY_MEMBER where Phone1='"+phone1.getText()+"'or Email='"+email.getText()+"' OR NID='"+nid.getText()+"'";
            ResultSet resultset = statement.executeQuery(query);
            if(resultset.next()){
                if(!resultset.getString("count").equals("0")){
                    JOptionPane.showMessageDialog(this, "Phone number already exists!");
                    return;
                }
            }    
            }catch(Exception e){
                e.printStackTrace();
            }
              
              if(name.getText().isEmpty() | phone1.getText().isEmpty() | email.getText().isEmpty() | dob == null | bg == null | nid.getText().isEmpty() | fnum.getText().isEmpty() | padd.getText().isEmpty() | occupation.getText().isEmpty()){
             JOptionPane.showMessageDialog(null, "Required fields must be filled!");
        
         } 
         
          //name
         if(!isWord(name.getText())){
             //name_l.setText("Invalid name input!");
             JOptionPane.showMessageDialog(this, "Invalid name");
             return;
             //name.setText("");
         }else {
             ps.setString(1, name.getText());
         }
         
         //phone 1
           
         
         if(phone1.getText().length() != 11)
         {
             //phone1_l.setText("Number length must be 11!");
             JOptionPane.showMessageDialog(this, "Number length must be 11!");
             return;
         }else{
             if(phone1.getText().startsWith("015") | phone1.getText().startsWith("017") | phone1.getText().startsWith("018") | phone1.getText().startsWith("019") | phone1.getText().startsWith("013") )
         {
             //phone1_l.setText("Invalid phone input!");
             //phone1.setText("");
             ps.setString(2, phone1.getText());
         }else{
              JOptionPane.showMessageDialog(this, "Invalid phone number");
             return; 
           }
             
         }

          //phone 2
           ps.setString(3, phone2.getText());
            
          
           //email
     //      if(!isEmail(email.getText())){
             //email_l.setText("Invalid email input!");
             //email.setText("");
      //       JOptionPane.showMessageDialog(this, "Invalid email input!");
     //        return;
      //   }else{
                ps.setString(4, email.getText());
      //         }
        SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdft.format(dob.getDate());
        ps.setString(5, date);

        String blood;
        blood=bg.getSelectedItem().toString();
        if(!blood.equals("Null")){
            ps.setString(6, blood);
        }else{
            JOptionPane.showMessageDialog(this, "Please choose blood group.");
            return;
        }
          
            //nid
         if(nid.getText().length()>17 | nid.getText().length()<10)
         {
             //nid_l.setText("NID length must be 10!");
             JOptionPane.showMessageDialog(this, "NID length must be 10!");
             return;
         }else{
                ps.setString(7, nid.getText());
         }

         //flat number
         if(!isF_Num(fnum.getText())){
             JOptionPane.showMessageDialog(this, "Invalid Flat Number!");
             return;
         }else{
           ps.setString(8, fnum.getText());
              
         }
           
        
           //permanent address
         if(!isWord(padd.getText()))
         {
             //padd_l.setText("Invalid permanent address input!");
             //padd.setText("");
             JOptionPane.showMessageDialog(this, "Invalid permanent address input!");
             return;
         }else{
                ps.setString(9, padd.getText());
         }
         
          
         //occupation
           if(!isWord(occupation.getText()))
         {
             //occupation_l.setText("Invalid occupation input!");
             //occupation.setText("");
             JOptionPane.showMessageDialog(this, "Invalid occupation input!");
             return;
         }else{
                ps.setString(10, occupation.getText());
           }
           
        //sub name
           if(!isWord(sname.getText()))
         {
             //sname_l.setText("Invalid permanent address input!");
             //sname.setText("");
             JOptionPane.showMessageDialog(this, "Invalid permanent address input!");
             return;
         }else{
                ps.setString(11, sname.getText());
           }
           
           
          //sub phone   
        
        
         if(sphone.getText().length() != 11 )
         {
             //sphone_l.setText("Number length must be 11!");
             JOptionPane.showMessageDialog(this, "Number length must be 11!");
             return;
         }else{
             if(sphone.getText().startsWith("015") | sphone.getText().startsWith("017") | sphone.getText().startsWith("018") | sphone.getText().startsWith("019") | sphone.getText().startsWith("013") )
         {
             //sphone_l.setText("Invalid phone input!");
             //sphone.setText("");
             ps.setString(12, sphone.getText());
         }else{
                 JOptionPane.showMessageDialog(this, "Invalid phone input!");
                return;
             }
                
                
         }
        
         //sub email
     //    if(!isEmail(semail.getText())){
             //semail_l.setText("Invalid email input!");
             //semail.setText("");
     //        JOptionPane.showMessageDialog(this, "Invalid email input!");
     //        return;
     //    } else{
            ps.setString(13, semail.getText());
     //    }
         
           int start_month = jMonthChooser1.getMonth()+1;
           int start_year = jYearChooser1.getYear();
           ps.setString(14, String.valueOf(start_month));
           ps.setString(15, String.valueOf(start_year));
           ps.setString(16, "1");
           ps.executeUpdate();
           
           String smi="";
           query = "SELECT TOP 1 * from SOCIETY_MEMBER ORDER BY SMI DESC";
           Statement statement = connection.createStatement(); 
           ResultSet resultset = statement.executeQuery(query);
           if(resultset.next()){
               smi = resultset.getString("SMI");
           }
           query = "INSERT INTO FLAT(F_Num, SMI) VALUES ('"+fnum.getText()+"', "+smi+")";
           statement.executeUpdate(query);

           //displayiing info directly after saving
           DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
           model.setRowCount(0);
           show_user();
           JOptionPane.showMessageDialog(null, "Society Member has been registered successfully!\n");
           erase();
           
        } catch (Exception e) {
           // JOptionPane.showMessageDialog(null, e);
           System.out.println(" "+e);
        }
        
        
        
    }//GEN-LAST:event_saveActionPerformed

    private void resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetActionPerformed
         erase();
    }//GEN-LAST:event_resetActionPerformed

    private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection  connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=HSMS_Project;selectMethod=cursor", "sa", "123456");
            int row = jTable2.getSelectedRow();
            String value = smiSearch.getText().toString();
            String query = "UPDATE SOCIETY_MEMBER SET Name=?, Phone1=?, Phone2=?, Email=?, P_Add=?, Job=?, S_Name=?, S_Phone=?, S_Email=? WHERE SMI="+value+"";
            
            java.sql.PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, name_e.getText());
            ps.setString(2, phone1_e.getText());
            ps.setString(3, phone2_e.getText());
            ps.setString(4, email_e.getText());
            ps.setString(5, padd_e.getText());
            ps.setString(6, occupation_e.getText());
            ps.setString(7, sname_e.getText());
            ps.setString(8, sphone_e.getText());
            ps.setString(9, semail_e.getText());

           
           ps.executeUpdate();
           
           //displayiing info directly after saving
           DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
           model.setRowCount(0);
           show_user();
          JOptionPane.showMessageDialog(null, "Member Information Updated Successfully!");
            erase1();
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
      }
    }//GEN-LAST:event_updateActionPerformed

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
         
        jTable2.setEnabled(true);
        
        int boxDelete = JOptionPane.showConfirmDialog(null, "No longer a society member?", "Confirmation",
				JOptionPane.YES_NO_OPTION);
        if(boxDelete == 0){
            try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection  connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=HSMS_Project;selectMethod=cursor", "sa", "123456");
            Statement statement = connection.createStatement();
            int row = jTable2.getSelectedRow();
            String select_smi = (jTable2.getModel().getValueAt(row, 0).toString());
            statement.executeUpdate("UPDATE SOCIETY_MEMBER SET State=2 where SMI="+select_smi);
            show_user();
//int row = jTable2.getSelectedRow();
            
            /*int row = jTable2.getSelectedRow();
                       String select_smi = (jTable2.getModel().getValueAt(row, 0).toString());
            String query1 = "insert into table_former(SMI, Name, Phone1, Phone2, Email,  DOB, BG, NID, F_Num, P_Add, Occupation, Sub_Name, Sub_Phone, Sub_Email) values(?,?, ?, ?, ?, ?, ?, ?,?,?,?,?,?, ?)";
            java.sql.PreparedStatement ps1 = connection.prepareStatement(query1);

            ps1.setString(1, jTable2.getModel().getValueAt(row, 0).toString());
            ps1.setString(2, jTable2.getModel().getValueAt(row, 1).toString());
            ps1.setString(3, jTable2.getModel().getValueAt(row, 2).toString());
            ps1.setString(4, jTable2.getModel().getValueAt(row, 3).toString());
            ps1.setString(5, jTable2.getModel().getValueAt(row, 4).toString());
            
            
           //SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM-dd");
          /// String date = sdft.format(dob_e.getDate());
            ps1.setString(6, jTable2.getModel().getValueAt(row, 5).toString());
            
          // String blood;
          /// blood=bg_e.getSelectedItem().toString();
            ps1.setString(7, jTable2.getModel().getValueAt(row, 6).toString());
           
           
            ps1.setString(8, jTable2.getModel().getValueAt(row, 7).toString());
            ps1.setString(9, jTable2.getModel().getValueAt(row, 8).toString());
            ps1.setString(10, jTable2.getModel().getValueAt(row, 9).toString());
            ps1.setString(11, jTable2.getModel().getValueAt(row, 10).toString());
            ps1.setString(12, jTable2.getModel().getValueAt(row, 11).toString());
            ps1.setString(13, jTable2.getModel().getValueAt(row, 12).toString());
            ps1.setString(14, jTable2.getModel().getValueAt(row, 13).toString());

            ps1.executeUpdate();
            
            //String value = (jTable2.getModel().getValueAt(row, 0).toString());
            String query = "DELETE FROM SOCIETY_MEMBER WHERE SMI="+select_smi;
            java.sql.PreparedStatement ps = connection.prepareStatement(query);
            ps.executeUpdate();
           
             //displayiing info directly after saving
            DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
            model.setRowCount(0);
            show_user();
            JOptionPane.showMessageDialog(null, "Deleted Successfully!");
            erase1();*/
            
         }catch(Exception e){
              JOptionPane.showMessageDialog(null, e);
         }
        }
    }//GEN-LAST:event_deleteActionPerformed

    private void searchBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtn1ActionPerformed
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection  connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=HSMS_Project;selectMethod=cursor", "sa", "123456");
            String sql = "SELECT * FROM SOCIETY_MEMBER WHERE SMI=? and State=1";
            java.sql.PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, smiSearch.getText());
            ResultSet rs = ps.executeQuery();
           
            if(rs.next()){
                String setName = rs.getString("Name");
                name_e.setText(setName);
                
                String setPhone1 = rs.getString("Phone1");
                phone1_e.setText(setPhone1);
                
                String setPhone2 = rs.getString("Phone2");
                phone2_e.setText(setPhone2);
                
                String setEmail = rs.getString("Email");
                email_e.setText(setEmail);
                  
                String setPerAddress = rs.getString("P_Add");
                padd_e.setText(setPerAddress);

                String setJob = rs.getString("Job");
                occupation_e.setText(setJob);

                sname_e.setText(rs.getString("S_Name"));
                sphone_e.setText(rs.getString("S_Phone"));
                semail_e.setText(rs.getString("S_Email"));
                
            }
           
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_searchBtn1ActionPerformed

    private void clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearActionPerformed
        erase1();
    }//GEN-LAST:event_clearActionPerformed

    private void smiSearch1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_smiSearch1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_smiSearch1ActionPerformed

    private void searchBtn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtn4ActionPerformed
        // TODO add your handling code here:
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection  connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=HSMS_Project;selectMethod=cursor", "sa", "123456");
            Statement statement = connection.createStatement();;
            String name = nameSearch2.getText();
            String sql = "SELECT * FROM SOCIETY_MEMBER WHERE Name like '%"+name+"%'";
            //java.sql.PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery(sql);
           
            if(rs.next()){
                String setName = rs.getString("Name");
                name_e.setText(setName);
                
                String setPhone1 = rs.getString("Phone1");
                phone1_e.setText(setPhone1);
                
                String setPhone2 = rs.getString("Phone2");
                phone2_e.setText(setPhone2);
                
                String setEmail = rs.getString("Email");
                email_e.setText(setEmail);
                  
                String setPerAddress = rs.getString("P_Add");
                padd_e.setText(setPerAddress);

                String setJob = rs.getString("Job");
                occupation_e.setText(setJob);

                sname_e.setText(rs.getString("S_Name"));
                sphone_e.setText(rs.getString("S_Phone"));
                semail_e.setText(rs.getString("S_Email"));
                
            }
           
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_searchBtn4ActionPerformed

    private void searchBtn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtn3ActionPerformed
        show_user_NAME();
    }//GEN-LAST:event_searchBtn3ActionPerformed

    private void smiSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_smiSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_smiSearchActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        show_user_SMI();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        show_user();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        gotoMainMenu();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        gotoMainMenu();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        gotoMainMenu();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        gotoMainMenu();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        int count = 0;
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection  connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=HSMS_Project;selectMethod=cursor", "sa", "123456");
            Statement statement = connection.createStatement();
            
            String smi1 = jTextField3.getText().toString();
            String smi2 = jTextField7.getText().toString();
            String smi2_old = jTextField9.getText().toString();
            String smi1_old = jTextField5.getText().toString();
            
            String check_query = "SELECT * from FLAT where F_Num like '"+smi1_old+"' and SMI = "+smi1+"";
            ResultSet resultset = statement.executeQuery(check_query);
            if(!resultset.next()){
                JOptionPane.showMessageDialog(this, "Invalid flat or SMI for first input");
                count++;
            }
            check_query = "SELECT * from FLAT where F_Num like '"+smi2_old+"' and SMI = "+smi2+"";
            resultset = statement.executeQuery(check_query);
            if(!resultset.next()){
                JOptionPane.showMessageDialog(this, "Invalid flat or SMI for second input");
                count++;
            }
            if(count==0){
                String query = "UPDATE FLAT SET F_Num = '"+smi2_old+"' where SMI = "+smi1+" and F_Num='"+smi1_old+"'";
                statement.executeUpdate(query);
                query = "UPDATE FLAT SET F_Num = '"+smi1_old+"' where SMI = "+smi2+" and F_Num='"+smi2_old+"'";
                statement.executeUpdate(query);
                JOptionPane.showMessageDialog(this, "Exchanged flat records successfully");
            }
            
         }catch(Exception e){
              JOptionPane.showMessageDialog(null, e);
         }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        show_flat_list_SMI();
        //show_flat_list_FLAT();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        show_flat_list_NAME();
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
        show_flat_list_FLAT();
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
        model.setRowCount(0);
        jTextField8.setText("");
        jTextField10.setText("");
        jTextField11.setText("");
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
        jTable1.setEnabled(true);
        
        int boxDelete = JOptionPane.showConfirmDialog(null, "Do you want to delete?", "Confirmation",
				JOptionPane.YES_NO_OPTION);
        if(boxDelete == 0){
            try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection  connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=HSMS_Project;selectMethod=cursor", "sa", "123456");
            int row = jTable1.getSelectedRow();
            String value = (jTable1.getModel().getValueAt(row, 0).toString());
            String query = "DELETE FROM FLAT WHERE F_Num like '"+value+"'";
            java.sql.PreparedStatement ps = connection.prepareStatement(query);
            ps.executeUpdate();
           
             //displayiing info directly after saving
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);
            //show_user();
            JOptionPane.showMessageDialog(null, "Deleted Successfully!");
            //erase1();
            
         }catch(Exception e){
              JOptionPane.showMessageDialog(null, e);
         }
        }
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        int count=0;
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection  connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=HSMS_Project;selectMethod=cursor", "sa", "123456");
            Statement statement = connection.createStatement();
            
            String smi = jTextField2.getText().toString();
            String num = jTextField1.getText().toString();
            
            String check_query = "SELECT * from FLAT where F_Num like '"+num+"'";
            ResultSet resultset = statement.executeQuery(check_query);
            if(resultset.next()){
                JOptionPane.showMessageDialog(this, "Flat already registered");
                count++;
            }
            
            if(count==0){
                String query = "INSERT INTO FLAT(F_Num, SMI) VALUES ('"+num+"', "+smi+")";
                statement.executeUpdate(query);
                JOptionPane.showMessageDialog(this, "Flat registered successfully");
                jTextField2.setText("");
                jTextField1.setText("");
            }
         }catch(Exception e){
              JOptionPane.showMessageDialog(null, e);
         }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void printSocFeeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printSocFeeBtnActionPerformed
        try {
            // TODO add your handling code here:
            boolean complete = jTextArea1.print();
            if(complete){
                JOptionPane.showMessageDialog(null, "Done Printing", "Information", JOptionPane.ERROR_MESSAGE);
            }
        } catch (PrinterException ex) {
            Logger.getLogger(SocietyFees.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_printSocFeeBtnActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        try{
            Connection connection;
            connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=HSMS_Project;selectMethod=cursor", "sa", "123456");
            Statement statement = connection.createStatement();
  
            String query;
            query = "SELECT TOP 1 * from SOCIETY_MEMBER ORDER BY SMI DESC";
            
            ResultSet resultset = statement.executeQuery(query);
            if(resultset.next()){
                jTextArea1.setText("\t\tSOCIETY MEMBER\n\tREGISTRATION COMPLETION CARD\n\n"
                + "SMI:\t"+resultset.getInt("SMI")+"\n"
                + "Name:\t"+resultset.getString("Name")+"\n"
                + "Email:\t"+resultset.getString("Email")+"\n"
                + "Phone(Personal):\t"+resultset.getString("Phone1")+"\n"
                + "Phone(Office):\t\t"+resultset.getString("Phone2")+"\n"
                + "Blood Group:\t\t"+resultset.getString("BG")+"\n"
                + "Society Flat Number:\t"+resultset.getString("F_Num")+"\n"
                + "Membership from:\t"+resultset.getString("StartMont")+", "+resultset.getString("StartYear")+"\n\n"
                + "\t-----Emergency Contatct-----\n\n"
                + "Name:\t"+resultset.getString("S_Name")+"\n"
                + "Phone:\t"+resultset.getString("S_Phone")+"\n"
                + "Email:\t"+resultset.getString("S_Email")+"\n"
                + "\n*Please check if there are any mistakes \nin the information before giving signature*\n\n\n"
                + "____________\t\t____________\n"
                + "Card Holder\t\tOffice"    
                );
            }
            
            jTextArea1.setVisible(true);
            printSocFeeBtn.setVisible(true);
        }catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        // TODO add your handling code here:
        jTextArea1.setText("");
    }//GEN-LAST:event_jButton15ActionPerformed

    private void showBtnFormerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showBtnFormerActionPerformed
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection  connection = DriverManager.getConnection(
                "jdbc:sqlserver://localhost:1433;databaseName=HSMS_Project;selectMethod=cursor", "sa", "123456");

            Statement st = connection.createStatement();
            String sql = "select * from SOCIETY_MEMBER where State=2";

            ResultSet rs = st.executeQuery(sql);

            while(rs.next()){
                String smi_show = String.valueOf(rs.getInt("SMI"));
                String name_show = rs.getString("Name");
                String phone1_show = rs.getString("Phone1");
                String phone2_show = rs.getString("Phone2");
                String email_show = rs.getString("Email");
                String dob_show = rs.getString("DOB");
                String bg_show = rs.getString("BG");
                String nid_show = rs.getString("NID");
                String f_num_show = rs.getString("F_Num");
                String p_add_show = rs.getString("P_Add");
                String occupation_show = rs.getString("Job");
                String s_name_show = rs.getString("S_Name");
                String s_phone_show = rs.getString("S_Phone");
                String s_email_show = rs.getString("S_Email");

                String tblData[] = {smi_show ,name_show , phone1_show, phone2_show, email_show, dob_show, bg_show, nid_show, f_num_show, p_add_show, occupation_show,
                    s_name_show,  s_phone_show , s_email_show };
                DefaultTableModel model1 = (DefaultTableModel) former_table.getModel();
                model1.addRow(tblData);
                showBtnFormer.setEnabled(false);

            }

            connection.close();

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_showBtnFormerActionPerformed

    private void hide_formerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hide_formerActionPerformed
        DefaultTableModel model1 = (DefaultTableModel) former_table.getModel();
        model1.setRowCount(0);
        showBtnFormer.setEnabled(true);
    }//GEN-LAST:event_hide_formerActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        // TODO add your handling code here:
        gotoMainMenu();
    }//GEN-LAST:event_jButton16ActionPerformed

    private void nameSearch1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nameSearch1KeyReleased
        // TODO add your handling code here:
        DefaultListModel model = new DefaultListModel();
        jList1.setModel(model);    
        try{
            Connection connection;
            connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=HSMS_Project;selectMethod=cursor", "sa", "123456");
            Statement statement = connection.createStatement();
            //ResultSet resultSet = statement.executeQuery("select SOCIETY_MEMBER.Name from SOCIETY_MEMBER"); 
            
            String search = nameSearch1.getText().trim();

            model.removeAllElements();
            if(!search.equals("")){
                ResultSet resultSet = statement.executeQuery("select Name from SOCIETY_MEMBER where Name like '%"+search+"%' and State=1"); 
                while(resultSet.next()){
                    String name = resultSet.getString("Name");
                    model.addElement(name);
                }
            }
            
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        jPopupMenu1.show(nameSearch1, 0, nameSearch1.getHeight());
        jList1.addMouseListener(new MouseAdapter() {
         public void mouseClicked(MouseEvent me) {
            jPopupMenu1.setVisible(false);
            if (me.getClickCount() == 1) {
               JList target = (JList)me.getSource();
               int index = target.locationToIndex(me.getPoint());
               if (index >= 0) {
                  Object item = target.getModel().getElementAt(index);
                  nameSearch1.setText(item.toString());
               }
            }
         }
      });
    }//GEN-LAST:event_nameSearch1KeyReleased

    private void nameSearch2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nameSearch2KeyReleased
        // TODO add your handling code here:
        DefaultListModel model = new DefaultListModel();
        jList1.setModel(model);    
        try{
            Connection connection;
            connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=HSMS_Project;selectMethod=cursor", "sa", "123456");
            Statement statement = connection.createStatement();
            //ResultSet resultSet = statement.executeQuery("select SOCIETY_MEMBER.Name from SOCIETY_MEMBER"); 
            
            String search = nameSearch2.getText().trim();

            model.removeAllElements();
            if(!search.equals("")){
                ResultSet resultSet = statement.executeQuery("select Name from SOCIETY_MEMBER where Name like '%"+search+"%' and State=1"); 
                while(resultSet.next()){
                    String name = resultSet.getString("Name");
                    model.addElement(name);
                }
            }
            
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        jPopupMenu1.show(nameSearch2, 0, nameSearch2.getHeight());
        jList1.addMouseListener(new MouseAdapter() {
         public void mouseClicked(MouseEvent me) {
            jPopupMenu1.setVisible(false);
            if (me.getClickCount() == 1) {
               JList target = (JList)me.getSource();
               int index = target.locationToIndex(me.getPoint());
               if (index >= 0) {
                  Object item = target.getModel().getElementAt(index);
                  nameSearch2.setText(item.toString());
               }
            }
         }
      });
    }//GEN-LAST:event_nameSearch2KeyReleased

    private void jTextField11KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField11KeyReleased
        // TODO add your handling code here:
        DefaultListModel model = new DefaultListModel();
        jList1.setModel(model);    
        try{
            Connection connection;
            connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=HSMS_Project;selectMethod=cursor", "sa", "123456");
            Statement statement = connection.createStatement();
            //ResultSet resultSet = statement.executeQuery("select SOCIETY_MEMBER.Name from SOCIETY_MEMBER"); 
            
            String search = jTextField11.getText().trim();

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
        
        jPopupMenu1.show(jTextField11, 0, jTextField11.getHeight());
        jList1.addMouseListener(new MouseAdapter() {
         public void mouseClicked(MouseEvent me) {
            jPopupMenu1.setVisible(false);
            if (me.getClickCount() == 1) {
               JList target = (JList)me.getSource();
               int index = target.locationToIndex(me.getPoint());
               if (index >= 0) {
                  Object item = target.getModel().getElementAt(index);
                  jTextField11.setText(item.toString());
               }
            }
         }
      });
    }//GEN-LAST:event_jTextField11KeyReleased
    public void gotoMainMenu(){
        OpeningDisplay od = new OpeningDisplay();
        od.setVisible(true);
        this.setVisible(false);
    }
    public static boolean isWord(String in){
        return Pattern.matches("[a-zA-Z .]+",in);
    }
    public static boolean isF_Num(String in){
        return Pattern.matches("[A-Z]+-+[0-9]+",in);
    }

    public static boolean isEmail(String in){
        return Pattern.matches("^[_A-Za-z0-9-\\\\+]+(\\\\.[_A-Za-z0-9-]+)*@\"\n" +
  "        + \"[A-Za-z0-9-]+(\\\\.[A-Za-z0-9]+)*(\\\\.[A-Za-z]{2,})$", in);
    }
    public static void main(String args[]) {
        
       try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Donation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Donation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Donation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Donation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold> 
      
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SocietyMembers().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel MT_P;
    private javax.swing.JPanel Reg_P;
    private javax.swing.JPanel Search_P;
    private javax.swing.JComboBox<String> bg;
    private javax.swing.JButton clear;
    private javax.swing.JButton delete;
    private com.toedter.calendar.JDateChooser dob;
    private javax.swing.JTextField email;
    private javax.swing.JTextField email_e;
    private javax.swing.JTextField fnum;
    private javax.swing.JTable former_table;
    private javax.swing.JButton hide_former;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList<String> jList1;
    private com.toedter.calendar.JMonthChooser jMonthChooser1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private com.toedter.calendar.JYearChooser jYearChooser1;
    private javax.swing.JTextField name;
    private javax.swing.JTextField nameSearch1;
    private javax.swing.JTextField nameSearch2;
    private javax.swing.JTextField name_e;
    private javax.swing.JTextField nid;
    private javax.swing.JTextField occupation;
    private javax.swing.JTextField occupation_e;
    private javax.swing.JTextField padd;
    private javax.swing.JTextField padd_e;
    private javax.swing.JTextField phone1;
    private javax.swing.JTextField phone1_e;
    private javax.swing.JTextField phone2;
    private javax.swing.JTextField phone2_e;
    private javax.swing.JButton printSocFeeBtn;
    private javax.swing.JButton reset;
    private javax.swing.JButton save;
    private javax.swing.JButton searchBtn1;
    private javax.swing.JButton searchBtn3;
    private javax.swing.JButton searchBtn4;
    private javax.swing.JTextField semail;
    private javax.swing.JTextField semail_e;
    private javax.swing.JButton showBtnFormer;
    private javax.swing.JTextField smiSearch;
    private javax.swing.JTextField smiSearch1;
    private javax.swing.JTextField sname;
    private javax.swing.JTextField sname_e;
    private javax.swing.JTextField sphone;
    private javax.swing.JTextField sphone_e;
    private javax.swing.JButton update;
    // End of variables declaration//GEN-END:variables
}
