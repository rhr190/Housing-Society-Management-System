/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hsms_project;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PrinterException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author Asus
 */
public class SocietyFees extends javax.swing.JFrame {

    Connection connection;
    String useramount, userCarFee, userBikeFee;
    /**
     * Creates new form SocietyFees
     */
    public SocietyFees() {
        initComponents();
        show_fees_update_list();
        jTextArea1.setVisible(false);
        printSocFeeBtn.setVisible(false);
        jPopupMenu1.add(jPanel6);
        
        
    }

    //Fees Record - Searching through Month and Year
    public ArrayList<FeesClass> feesList(){
        ArrayList<FeesClass> feesList= new ArrayList<>();
        try{
            connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=HSMS_Project;selectMethod=cursor", "sa", "123456");
            Statement statement = connection.createStatement();
            
            DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MM");
            String monthString = monthFormatter.format(Month.of(jMonthChooser_FeesRecord.getMonth() + 1));
            //int month = Integer.valueOf(monthString);
            int year = jYearChooser_FeesRecord.getYear();
            String yearString = String.valueOf(year);
            System.out.println(monthString);
            System.out.println(year);
            
            String query;
            query = "select FEES.Month, FEES.Year, FEES.Amount,FEES.DOP,SOCIETY_MEMBER.Name,FEES.SMI "
                    + "from FEES inner join SOCIETY_MEMBER on FEES.SMI = SOCIETY_MEMBER.SMI "
                    + "where FEES.Month = "+monthString+" and FEES.Year = "+yearString+" order by DOP";
            
            ResultSet resultset = statement.executeQuery(query);
            FeesClass feesclass;
            while(resultset.next()){
                feesclass = new FeesClass(resultset.getInt("Month"),resultset.getInt("Year"),resultset.getInt("Amount"), resultset.getString("DOP"),resultset.getString("Name"), resultset.getInt("SMI"));
                feesList.add(feesclass);
                
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return feesList;
    }
    public void show_fees_list(){
        ArrayList<FeesClass> fees = feesList(); 
        DefaultTableModel model = (DefaultTableModel)FeesRecordTable.getModel();
        model.setRowCount(0);
        Object[] row = new Object[6];
        for(int i=0; i<fees.size(); i++){
            row[0] = fees.get(i).getMonth();
            row[1] = fees.get(i).getYear();
            row[2] = fees.get(i).getAmount();
            row[3] = fees.get(i).getDOP();
            row[4] = fees.get(i).getName();
            row[5] = fees.get(i).getSMI();
            model.addRow(row);
            
        }
    }
    
    //Due Fees Record - Seeing who are due in specific month and year
    public ArrayList<DueFeesClass> duefeesList(){
        ArrayList<DueFeesClass> duefeesList= new ArrayList<>();
        try{
            connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=HSMS_Project;selectMethod=cursor", "sa", "123456");
            Statement statement = connection.createStatement();
            int month = jMonthChooser2.getMonth() + 1;
            DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MM");
            String monthString = monthFormatter.format(Month.of(jMonthChooser2.getMonth() + 1));
            int year = jYearChooser2.getYear();
            int monthYearCalculate = (year*100)+month;
            String monthYearString = String.valueOf(monthYearCalculate);
            String yearString = String.valueOf(year);
            String query;
            query = "SELECT SOCIETY_MEMBER.SMI, SOCIETY_MEMBER.Name, SOCIETY_MEMBER.Phone1, SOCIETY_MEMBER.Phone2, SOCIETY_MEMBER.Email from SOCIETY_MEMBER " +
                    "where SOCIETY_MEMBER.SMI not in " +
                    "( Select FEES.SMI from FEES where FEES.Month = "+monthString+" and FEES.Year = "+yearString+")" +
                    " and SOCIETY_MEMBER.SMI in " +
                    "( Select SMI from SOCIETY_MEMBER where (StartYear*100)+StartMont <= "+monthYearString+")"
                    + " and SOCIETY_MEMBER.State=1";
            ResultSet resultset = statement.executeQuery(query);
            DueFeesClass duefeesclass;
            while(resultset.next()){
                duefeesclass = new DueFeesClass(resultset.getInt("SMI"), resultset.getString("Name"), resultset.getString("Phone1"), resultset.getString("Phone2"),resultset.getString("Email"));
                duefeesList.add(duefeesclass);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return duefeesList;
    }
    public void show_due_fees_list(){
        ArrayList<DueFeesClass> duefees = duefeesList(); 
        DefaultTableModel model = (DefaultTableModel)SearchDueFeesTable.getModel();
        model.setRowCount(0);
        Object[] row = new Object[5];
        for(int i=0; i<duefees.size(); i++){
            row[0] = duefees.get(i).getSMI();
            row[1] = duefees.get(i).getName();
            row[2] = duefees.get(i).getPhone1();
            row[3] = duefees.get(i).getPhone2();
            row[4] = duefees.get(i).getEmail();
            
            model.addRow(row);
            
        }
    }
    
    //Search Individual Paid - All history of fees payment-using SMI
    public ArrayList<FeesIndividualClass> individualfeesList(){
        ArrayList<FeesIndividualClass> individualfeesList= new ArrayList<>();
        try{
            connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=HSMS_Project;selectMethod=cursor", "sa", "123456");
            Statement statement = connection.createStatement();
           
            int smi = Integer.valueOf(jTextField4.getText());
            String smiString = String.valueOf(smi);
            
            String query;
            query = "Select FEES.SMI, SOCIETY_MEMBER.Name, FEES.DOP, FEES.Month, FEES.Year, FEES.Amount "
                    + "from FEES inner join SOCIETY_MEMBER on FEES.SMI = SOCIETY_MEMBER.SMI "
                    + "where FEES.SMI = "+smiString+" order by Month, DOP";
            ResultSet resultset = statement.executeQuery(query);
            FeesIndividualClass individualfeesclass;
            while(resultset.next()){                 
                individualfeesclass = new FeesIndividualClass(resultset.getInt("SMI"), resultset.getString("Name"), resultset.getString("DOP"), resultset.getInt("Month"),resultset.getInt("Year"), resultset.getInt("Amount"));
                individualfeesList.add(individualfeesclass);
                
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return individualfeesList;
    }
    public void show_fees_list_individual(){
        ArrayList<FeesIndividualClass> fees = individualfeesList(); 
        DefaultTableModel model = (DefaultTableModel)SearchIndividualPaidTable.getModel();
        model.setRowCount(0);
        Object[] row = new Object[6];
        for(int i=0; i<fees.size(); i++){
            row[0] = fees.get(i).getSMI();
            row[1] = fees.get(i).getName();
            row[2] = fees.get(i).getDOP();
            row[3] = fees.get(i).getMonth();
            row[4] = fees.get(i).getYear();
            row[5] = fees.get(i).getAmount();
            model.addRow(row);
            
        }
    }
    
    //Search Individual Paid - All history of fees payment-using Name
    public ArrayList<FeesIndividualClass> individualfeesnameList(){
        ArrayList<FeesIndividualClass> individualfeesnameList= new ArrayList<>();
        try{
            connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=HSMS_Project;selectMethod=cursor", "sa", "123456");
            Statement statement = connection.createStatement();
           
            //String name_string = String.valueOf(jComboBox3.getSelectedItem());
            String name_string = jTextField7.getText();
            String query;
            query = "Select FEES.SMI, SOCIETY_MEMBER.Name, FEES.DOP, FEES.Month, FEES.Year, FEES.Amount "
                    + "from FEES inner join SOCIETY_MEMBER on FEES.SMI = SOCIETY_MEMBER.SMI "
                    + "where SOCIETY_MEMBER.Name LIKE '"+name_string+"' order by Month, DOP";
            
            ResultSet resultset = statement.executeQuery(query);
            FeesIndividualClass individualfeesclass;
            while(resultset.next()){                        //String Name, String DOP, int SMI, int Month, int Year, int Amount
                individualfeesclass = new FeesIndividualClass(resultset.getInt("SMI"), resultset.getString("Name"), resultset.getString("DOP"), resultset.getInt("Month"),resultset.getInt("Year"), resultset.getInt("Amount"));
                individualfeesnameList.add(individualfeesclass);
                
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return individualfeesnameList;
    }
    public void show_fees_list_name_individual(){
        ArrayList<FeesIndividualClass> fees = individualfeesnameList(); 
        DefaultTableModel model = (DefaultTableModel)SearchIndividualPaidTable.getModel();
        model.setRowCount(0);
        Object[] row = new Object[6];
        for(int i=0; i<fees.size(); i++){
            row[0] = fees.get(i).getSMI();
            row[1] = fees.get(i).getName();
            row[2] = fees.get(i).getDOP();
            row[3] = fees.get(i).getMonth();
            row[4] = fees.get(i).getYear();
            row[5] = fees.get(i).getAmount();
            model.addRow(row);
            
        }
    }
    
    //Fees Updates - To see the updates of the changes in society fees
    public ArrayList<FeesRecordClass> feesUpdatesList(){
        ArrayList<FeesRecordClass> feesUpdateList= new ArrayList<>();
        try{
            connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=HSMS_Project;selectMethod=cursor", "sa", "123456");
            Statement statement = connection.createStatement();
            
            //DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MM");
            //String monthString = monthFormatter.format(Month.of(jMonthChooser_FeesRecord.getMonth() + 1));
            //int year = jYearChooser_FeesRecord.getYear();
            //String yearString = String.valueOf(year);
            //System.out.println(monthString);
            //System.out.println(year);
            
            String query;
            query = "select * from FEES_CAL";
            
            ResultSet resultset = statement.executeQuery(query);
            FeesRecordClass feesRecordClass;
            while(resultset.next()){
                feesRecordClass = new FeesRecordClass(resultset.getInt("Month"),resultset.getInt("Year"),resultset.getInt("Amount"), resultset.getInt("AmountCar"),resultset.getInt("AmountBike"), resultset.getString("Admin"));
                feesUpdateList.add(feesRecordClass);
                
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return feesUpdateList;
    }
    
    public void show_fees_update_list(){
        ArrayList<FeesRecordClass> fees = feesUpdatesList(); 
        DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
        model.setRowCount(0);
        Object[] row = new Object[6];
        for(int i=0; i<fees.size(); i++){
            row[0] = fees.get(i).getMonth();
            row[1] = fees.get(i).getYear();
            row[2] = fees.get(i).getBasicFee();
            row[3] = fees.get(i).getCarFee();
            row[4] = fees.get(i).getBikeFee();
            row[5] = fees.get(i).getAdmin();
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
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jMonthChooser1 = new com.toedter.calendar.JMonthChooser();
        jYearChooser1 = new com.toedter.calendar.JYearChooser();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        calculateSocFeeBtn = new javax.swing.JButton();
        insertSocFeeBtn = new javax.swing.JButton();
        printSocFeeBtn = new javax.swing.JButton();
        resetSocFeeInfoBtn = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton11 = new javax.swing.JButton();
        resetSocFeeInfoBtn1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jMonthChooser2 = new com.toedter.calendar.JMonthChooser();
        jLabel9 = new javax.swing.JLabel();
        jYearChooser2 = new com.toedter.calendar.JYearChooser();
        jScrollPane2 = new javax.swing.JScrollPane();
        SearchDueFeesTable = new javax.swing.JTable();
        searchDueFeesBtn = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        FeesRecordTable = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jMonthChooser_FeesRecord = new com.toedter.calendar.JMonthChooser();
        jLabel8 = new javax.swing.JLabel();
        jYearChooser_FeesRecord = new com.toedter.calendar.JYearChooser();
        searchFeesRecBtn = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        SearchIndividualPaidTable = new javax.swing.JTable();
        indvFeesSearchBtn = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        indvPaidSearchNameBtn = new javax.swing.JButton();
        jTextField7 = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton10 = new javax.swing.JButton();

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
        setTitle("Society Fees");

        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jPanel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Society Member Id (SMI)");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Society Fee - Month");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Society Fee - Year");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Date of payment");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("Total");

        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jTextField2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jMonthChooser1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jYearChooser1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jDateChooser1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        calculateSocFeeBtn.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        calculateSocFeeBtn.setText("Calculate");
        calculateSocFeeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calculateSocFeeBtnActionPerformed(evt);
            }
        });

        insertSocFeeBtn.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        insertSocFeeBtn.setText("Enter");
        insertSocFeeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertSocFeeBtnActionPerformed(evt);
            }
        });

        printSocFeeBtn.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        printSocFeeBtn.setText("Print");
        printSocFeeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printSocFeeBtnActionPerformed(evt);
            }
        });

        resetSocFeeInfoBtn.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        resetSocFeeInfoBtn.setText("Reset");
        resetSocFeeInfoBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetSocFeeInfoBtnActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setText("Society Fee");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel12.setText("Car Fee");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel13.setText("Bike Fee");

        jTextField3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jTextField5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });

        jTextField6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel14.setText("-------------------------------------");

        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        jTextArea1.setRows(5);
        jScrollPane4.setViewportView(jTextArea1);

        jButton11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton11.setText("Main Menu");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        resetSocFeeInfoBtn1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        resetSocFeeInfoBtn1.setText("Clear");
        resetSocFeeInfoBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetSocFeeInfoBtn1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(448, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(calculateSocFeeBtn)
                                .addGap(206, 206, 206))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(122, 122, 122))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel1)
                                        .addComponent(jLabel3))
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jYearChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jMonthChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(111, 111, 111))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(insertSocFeeBtn)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel12))
                                .addGap(21, 21, 21)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jTextField5)
                                        .addComponent(jTextField3)
                                        .addComponent(jTextField6)
                                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(resetSocFeeInfoBtn))
                                .addGap(131, 131, 131)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(79, 79, 79)
                                .addComponent(printSocFeeBtn)
                                .addGap(62, 62, 62)
                                .addComponent(resetSocFeeInfoBtn1)))
                        .addGap(341, 341, 341))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton11)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jButton11)
                .addGap(86, 86, 86)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jMonthChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jYearChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38)
                        .addComponent(calculateSocFeeBtn)
                        .addGap(51, 51, 51)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel14)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 486, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(79, 79, 79)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(insertSocFeeBtn)
                    .addComponent(printSocFeeBtn)
                    .addComponent(resetSocFeeInfoBtn)
                    .addComponent(resetSocFeeInfoBtn1))
                .addContainerGap(77, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Collect Fees", jPanel1);

        jPanel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("Month ");

        jMonthChooser2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setText("Year");

        SearchDueFeesTable.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        SearchDueFeesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "SMI", "Name", "Phone1", "Phone2", "Email"
            }
        ));
        jScrollPane2.setViewportView(SearchDueFeesTable);

        searchDueFeesBtn.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        searchDueFeesBtn.setIcon(new javax.swing.ImageIcon("E:\\3.1\\Database Lab\\HSMS_Project\\test\\search_icon.png")); // NOI18N
        searchDueFeesBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchDueFeesBtnActionPerformed(evt);
            }
        });

        jButton8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton8.setText("Main Menu");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1571, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButton8))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jMonthChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jYearChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(searchDueFeesBtn)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton8)
                        .addGap(36, 76, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jMonthChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jYearChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(searchDueFeesBtn))
                        .addGap(18, 18, 18)))
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 670, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Search Due Fees", jPanel3);

        jPanel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        FeesRecordTable.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        FeesRecordTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Month", "Year", "Amount", "DOP", "Name", "SMI"
            }
        ));
        jScrollPane1.setViewportView(FeesRecordTable);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setText("Month ");

        jMonthChooser_FeesRecord.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jMonthChooser_FeesRecord.setMinimumSize(new java.awt.Dimension(110, 25));
        jMonthChooser_FeesRecord.setPreferredSize(new java.awt.Dimension(100, 20));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setText("Year");

        searchFeesRecBtn.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        searchFeesRecBtn.setIcon(new javax.swing.ImageIcon("E:\\3.1\\Database Lab\\HSMS_Project\\test\\search_icon.png")); // NOI18N
        searchFeesRecBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchFeesRecBtnActionPerformed(evt);
            }
        });

        jButton7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton7.setText("Main Menu");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1571, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButton7))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jMonthChooser_FeesRecord, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jYearChooser_FeesRecord, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(searchFeesRecBtn)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton7)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap(56, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jYearChooser_FeesRecord, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jMonthChooser_FeesRecord, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(searchFeesRecBtn))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 670, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Fees Record", jPanel2);

        jPanel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel11.setText("SMI");

        jTextField4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        SearchIndividualPaidTable.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        SearchIndividualPaidTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "SMI", "Name", "DOP", "Month", "Year", "Amount"
            }
        ));
        jScrollPane3.setViewportView(SearchIndividualPaidTable);

        indvFeesSearchBtn.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        indvFeesSearchBtn.setIcon(new javax.swing.ImageIcon("E:\\3.1\\Database Lab\\HSMS_Project\\test\\search_icon.png")); // NOI18N
        indvFeesSearchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                indvFeesSearchBtnActionPerformed(evt);
            }
        });

        jButton9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton9.setText("Main Menu");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel15.setText("Name");

        indvPaidSearchNameBtn.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        indvPaidSearchNameBtn.setIcon(new javax.swing.ImageIcon("E:\\3.1\\Database Lab\\HSMS_Project\\test\\search_icon.png")); // NOI18N
        indvPaidSearchNameBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                indvPaidSearchNameBtnActionPerformed(evt);
            }
        });

        jTextField7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField7KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton9))
                    .addComponent(jScrollPane3))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(585, Short.MAX_VALUE)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(indvFeesSearchBtn)
                .addGap(40, 40, 40)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(indvPaidSearchNameBtn)
                .addGap(445, 445, 445))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jButton9)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(indvPaidSearchNameBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(1, 1, 1))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(indvFeesSearchBtn)
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 660, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Search Individual Paid", jPanel5);

        jPanel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jTable1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Month", "Year", "Basic Fee", "Car Fee", "Bike Fee", "Admin"
            }
        ));
        jScrollPane5.setViewportView(jTable1);

        jButton10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton10.setText("Main Menu");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 1571, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton10)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(jButton10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 86, Short.MAX_VALUE)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 660, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Fees Updates", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void calculateSocFeeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_calculateSocFeeBtnActionPerformed
        // TODO add your handling code here:
        Connection connection;
        if(jTextField1.getText().isEmpty() || jDateChooser1.getDate() == null){
            JOptionPane.showMessageDialog(this, "One or more fields are empty");
            return;
        }
        
        String smiString = jTextField1.getText();
        if(check_SMI(smiString)==false){
                JOptionPane.showMessageDialog(this, "Invalid SMI!");
                return;
            }
        
        String pattern  = "yyyy-MM-dd";      
        DateFormat formatter = new SimpleDateFormat(pattern);
        String dop = formatter.format(jDateChooser1.getDate());
        
        int total = 0;
        int month = jMonthChooser1.getMonth()+1;
        int year = jYearChooser1.getYear();
        int monthYearMix = (year*100)+month;
        
        String monthYearString = String.valueOf(monthYearMix);
        String monthString = String.valueOf(month);
        String yearString = String.valueOf(year);
        int amount = 0; 
        int amountCar = 0;
        int amountBike = 0;
        
        int countCar = 0;
        int countBike = 0;
        try{
            connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=HSMS_Project;selectMethod=cursor", "sa", "123456");
            Statement statement = connection.createStatement();
            String query = "Select TOP 1 Amount, AmountCar, AmountBike from FEES_CAL "
                    + "where Year*100+Month <= "+monthYearString+" order by Year*100+Month desc";
            //Select COUNT(SMI) as CountCar from VEHICLE "
                           //+ "where (Type = 'Car') and SMI = "+smiString+" and "+monthString+" >= EntryMonth and EntryYear <= "+yearString+"
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                amount = Integer.valueOf(resultSet.getString("Amount"));
                amountCar = Integer.valueOf(resultSet.getString("AmountCar"));
                amountBike = Integer.valueOf(resultSet.getString("AmountBike"));    
            }
            
            String query2 = "SELECT COUNT(*) as 'CountCar' from VEHICLE where SMI = "+smiString+" and EntryMonth <= "+monthString+" and SMI = ANY (SELECT SMI from VEHICLE where EntryYear <= "+yearString+") and Type = 'Car'";
            
            ResultSet resultSet2 = statement.executeQuery(query2);
            while(resultSet2.next()){
                countCar = Integer.valueOf(resultSet2.getString("CountCar"));
                    
            }
            
            String query3 = "SELECT COUNT(*) as 'CountBike' from VEHICLE where SMI = "+smiString+" and EntryMonth <= "+monthString+" and SMI = ANY (SELECT SMI from VEHICLE where EntryYear <= "+yearString+") and Type = 'Bike'";
            
            ResultSet resultSet3 = statement.executeQuery(query3);
            while(resultSet3.next()){
                countBike = Integer.valueOf(resultSet3.getString("CountBike"));  
            }
            
            
            total = amount + (amountCar * countCar) + (amountBike * countBike);
        
            useramount = String.valueOf(total);
            userCarFee = String.valueOf(amountCar * countCar);
            userBikeFee = String.valueOf(amountBike * countBike);

            jTextField3.setText(String.valueOf(amount));
            jTextField5.setText(String.valueOf(userCarFee));
            jTextField6.setText(String.valueOf(userBikeFee));
            jTextField2.setText(useramount);

            jTextField3.setEditable(false);
            jTextField5.setEditable(false);
            jTextField6.setEditable(false);
            jTextField2.setEditable(false);
            
        }catch(Exception e) {
            e.printStackTrace();
        }
        
        
        jTextArea1.setText("SOCIETY MONTHLY FEE INVOICE\n\n"
                + "Society Member Id:"+smiString+"\n"
                + "Month:\t\t"+monthString+"\n"
                + "Year:\t\t"+yearString+"\n"
                + "Basic Fee:\t"+amount+"\n"
                + "Car Fee:\t"+userCarFee+"\n"
                + "Bike Fee:\t"+userBikeFee+"\n\n"
                + "Total Fee:\t"+useramount+"\n\n\n"
                + "Date of payment:"+dop+"\n"
                );
        //show_fees_list_individual();
    }                                        

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
        
    }                                           

    /*private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        
        
    }//GEN-LAST:event_calculateSocFeeBtnActionPerformed
*/
    private void insertSocFeeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertSocFeeBtnActionPerformed
        // TODO add your handling code here:
        Connection connection;
        
        int smi = Integer.valueOf(jTextField1.getText());
        String smiString = String.valueOf(smi);
        int month = jMonthChooser1.getMonth()+1;
        int year = jYearChooser1.getYear();
        String monthString = String.valueOf(month);
        String yearString = String.valueOf(year);
        
        String pattern  = "yyyy-MM-dd";      
        DateFormat formatter = new SimpleDateFormat(pattern);
        String dop = formatter.format(jDateChooser1.getDate());
         
        /*Now checking if concerned SMI has already gievn his fees in that month of that year*/
        try {
            connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=HSMS_Project;selectMethod=cursor", "sa", "123456");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement
                   .executeQuery("SELECT COUNT(*) as Count FROM FEES WHERE "
                           + "SMI = "+smiString+" and "
                           + "Month = "+monthString+" and "
                           + "Year = "+yearString+"");           
            if (resultSet.next()) {
                if(!resultSet.getString("Count").equals("0")){
                    //System.out.println(resultSet.getString("Count"));
                    JOptionPane.showMessageDialog(this, "Record already exists.");
                    return;
                }             
            }            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        /*The query is being taken*/
        String query;
        query = "INSERT INTO FEES (SMI, Month, Year, Amount, DOP)" +
                          "values ("+smiString+", "+monthString+", "+yearString+", "+useramount+", '"+dop+"')";
       
        /*All is good, executing the query*/
        try {
            connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=HSMS_Project;selectMethod=cursor", "sa", "123456");
            
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            
        } catch (Exception e) {  e.printStackTrace(); }
        JOptionPane.showMessageDialog(this, "Record entered successfully.");
        
        reset_entries();
        
        jTextArea1.setVisible(true);
        printSocFeeBtn.setVisible(true);
        
    }//GEN-LAST:event_insertSocFeeBtnActionPerformed

    private void searchFeesRecBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchFeesRecBtnActionPerformed
        // TODO add your handling code here:
        show_fees_list();
    }//GEN-LAST:event_searchFeesRecBtnActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        gotoMainMenu();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void searchDueFeesBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchDueFeesBtnActionPerformed
        // TODO add your handling code here:
        show_due_fees_list();
    }//GEN-LAST:event_searchDueFeesBtnActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        gotoMainMenu();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void indvFeesSearchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_indvFeesSearchBtnActionPerformed

        if(jTextField4.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "SMI is not given");
            return;
        }
        String smiString = jTextField4.getText();
        if(check_SMI(smiString)==false){
            JOptionPane.showMessageDialog(this, "Invalid SMI!");
            return;
        }
        show_fees_list_individual();
        jTextField7.setText("");
    }//GEN-LAST:event_indvFeesSearchBtnActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        gotoMainMenu();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField5ActionPerformed

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

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        gotoMainMenu();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        gotoMainMenu();
    }//GEN-LAST:event_jButton11ActionPerformed

    private void indvPaidSearchNameBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_indvPaidSearchNameBtnActionPerformed
        // TODO add your handling code here:
        show_fees_list_name_individual();
        jTextField4.setText("");
    }//GEN-LAST:event_indvPaidSearchNameBtnActionPerformed

    //Suggest-box of names of Society Members for searching
    private void jTextField7KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField7KeyReleased
        // Searching through name
        DefaultListModel model = new DefaultListModel();
        jList1.setModel(model);    
        String search="";
        try{
            connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=HSMS_Project;selectMethod=cursor", "sa", "123456");
            Statement statement = connection.createStatement();
            //ResultSet resultSet = statement.executeQuery("select SOCIETY_MEMBER.Name from SOCIETY_MEMBER"); 
            
            search = jTextField7.getText().trim();

            model.removeAllElements();
            if(!search.equals("")){
                ResultSet resultSet = statement.executeQuery("select Name from SOCIETY_MEMBER where Name LIKE '%"+search+"%' and State=1"); 
                while(resultSet.next()){
                    String name = resultSet.getString("Name");
                    model.addElement(name);
                }
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        jPopupMenu1.show(jTextField7, 0, jTextField7.getHeight());
        jList1.addMouseListener(new MouseAdapter() {
         public void mouseClicked(MouseEvent me) {
            jPopupMenu1.setVisible(false);
            if (me.getClickCount() == 1) {
               JList target = (JList)me.getSource();
               int index = target.locationToIndex(me.getPoint());
               if (index >= 0) {
                  Object item = target.getModel().getElementAt(index);
                  jTextField7.setText(item.toString());
               }
            }
         }
      });
    }//GEN-LAST:event_jTextField7KeyReleased

    private void resetSocFeeInfoBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetSocFeeInfoBtnActionPerformed
        // TODO add your handling code here:
        reset_entries();
    }//GEN-LAST:event_resetSocFeeInfoBtnActionPerformed

    private void resetSocFeeInfoBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetSocFeeInfoBtn1ActionPerformed
        // TODO add your handling code here:
        jTextArea1.setText("");
    }//GEN-LAST:event_resetSocFeeInfoBtn1ActionPerformed

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
            java.util.logging.Logger.getLogger(SocietyFees.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SocietyFees.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SocietyFees.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SocietyFees.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SocietyFees().setVisible(true);
            }
        });
    }
    
    public static boolean isNumeric(String str) { 
      try {  
        Integer.parseInt(str);  
        return true;
      } catch(NumberFormatException e){  
        return false;  
      }  
    }
    public static boolean check_SMI(String smiString){
        int count=0;
        /*Ok, SMI(or something) given, now making sure it's a number*/
        if(!isNumeric(smiString)){
            count++;
        }
        
        /*Checking if SMI is valid or not*/
        try {
            Connection connection;
            connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=HSMS_Project;selectMethod=cursor", "sa", "123456");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement
                   .executeQuery("SELECT COUNT(*) as Count FROM SOCIETY_MEMBER WHERE SMI = "+smiString+" and State=1");           
            if (resultSet.next()) {
                if(resultSet.getString("Count").equals("0")){
                    count++;
                }             
            }
            
                        
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(count == 0){
            return true;
        }else{
            return false;
        }
    }
    public void gotoMainMenu(){
        OpeningDisplay open = new OpeningDisplay();
        open.setVisible(true);
        this.setVisible(false);
    }
    public void reset_entries(){
        jTextField1.setText("");
        jMonthChooser1.setMonth(0);
        jYearChooser1.setYear(2020);
        jDateChooser1.setDate(null);
        jTextField3.setText("");
        jTextField5.setText("");
        jTextField6.setText("");
        jTextField2.setText("");
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable FeesRecordTable;
    private javax.swing.JTable SearchDueFeesTable;
    private javax.swing.JTable SearchIndividualPaidTable;
    private javax.swing.JButton calculateSocFeeBtn;
    private javax.swing.JButton indvFeesSearchBtn;
    private javax.swing.JButton indvPaidSearchNameBtn;
    private javax.swing.JButton insertSocFeeBtn;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList<String> jList1;
    private com.toedter.calendar.JMonthChooser jMonthChooser1;
    private com.toedter.calendar.JMonthChooser jMonthChooser2;
    private com.toedter.calendar.JMonthChooser jMonthChooser_FeesRecord;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    public javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private com.toedter.calendar.JYearChooser jYearChooser1;
    private com.toedter.calendar.JYearChooser jYearChooser2;
    private com.toedter.calendar.JYearChooser jYearChooser_FeesRecord;
    private javax.swing.JButton printSocFeeBtn;
    private javax.swing.JButton resetSocFeeInfoBtn;
    private javax.swing.JButton resetSocFeeInfoBtn1;
    private javax.swing.JButton searchDueFeesBtn;
    private javax.swing.JButton searchFeesRecBtn;
    // End of variables declaration//GEN-END:variables
}
