/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hsms_project;
/*
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author Asus
 */
/*
public class Fees extends javax.swing.JFrame {
    public Connection connection;
    String useramount;
    
    
    /**
     * Creates new form Fees
     */
/*    public Fees() {
        initComponents();
        //set_fee();
        //show_fees_list();
    }
    /*public void connectDB() {
        try {
            connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=HSMS_Project;selectMethod=cursor", "sa", "123456");
            Statement statement = connection.createStatement();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
/*    public ArrayList<FeesClass> feesList(){
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
    
    public ArrayList<DueFeesClass> duefeesList(){
        ArrayList<DueFeesClass> duefeesList= new ArrayList<>();
        try{
            connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=HSMS_Project;selectMethod=cursor", "sa", "123456");
            Statement statement = connection.createStatement();
            
            DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MM");
            String monthString = monthFormatter.format(Month.of(jMonthChooser2.getMonth() + 1));
            //int month = Integer.valueOf(monthString);
            int year = jYearChooser2.getYear();
            String yearString = String.valueOf(year);
            //System.out.println(monthString);
            //System.out.println(year);
            
            String query;
            query = "SELECT SOCIETY_MEMBER.SMI, SOCIETY_MEMBER.Name, SOCIETY_MEMBER.Phone1, SOCIETY_MEMBER.Phone2, SOCIETY_MEMBER.Email from SOCIETY_MEMBER " +
                    "where SOCIETY_MEMBER.SMI not in ( Select FEES.SMI from FEES " +
                    "where FEES.Month = "+monthString+" and FEES.Year = "+yearString+")";
            //SELECT FEES.id, SOCIETY_MEMBER.SMI, SOCIETY_MEMBER.Name from FEES inner join SOCIETY_MEMBER on FEES.SMI = SOCIETY_MEMBER.SMI order by Month, DOP
//where FEES.Month = 'January' and FEES.Year = '2020'
            ResultSet resultset = statement.executeQuery(query);
            DueFeesClass duefeesclass;
            while(resultset.next()){
                duefeesclass = new DueFeesClass(resultset.getInt("SMI"), resultset.getString("Name"), resultset.getString("Phone1"), resultset.getString("Phone2"),resultset.getString("Email"));
                //duefeesList.add(duefeesclass);
                duefeesList.add(duefeesclass);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return duefeesList;
    }
    
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
            //SELECT FEES.id, SOCIETY_MEMBER.SMI, SOCIETY_MEMBER.Name from FEES inner join SOCIETY_MEMBER on FEES.SMI = SOCIETY_MEMBER.SMI order by Month, DOP
//where FEES.Month = 'January' and FEES.Year = '2020'
            ResultSet resultset = statement.executeQuery(query);
            FeesIndividualClass individualfeesclass;
            while(resultset.next()){                        //String Name, String DOP, int SMI, int Month, int Year, int Amount
                individualfeesclass = new FeesIndividualClass(resultset.getInt("SMI"), resultset.getString("Name"), resultset.getString("DOP"), resultset.getInt("Month"),resultset.getInt("Year"), resultset.getInt("Amount"));
                individualfeesList.add(individualfeesclass);
                
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return individualfeesList;
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
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     *//*
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mutableTableImpl1 = new net.sf.jtables.table.impl.MutableTableImpl();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jMonthChooser1 = new com.toedter.calendar.JMonthChooser();
        jYearChooser1 = new com.toedter.calendar.JYearChooser();
        jLabel9 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        FeesRecordTable = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jMonthChooser_FeesRecord = new com.toedter.calendar.JMonthChooser();
        jLabel8 = new javax.swing.JLabel();
        jYearChooser_FeesRecord = new com.toedter.calendar.JYearChooser();
        search_FeesRecord = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jMonthChooser2 = new com.toedter.calendar.JMonthChooser();
        jLabel6 = new javax.swing.JLabel();
        jYearChooser2 = new com.toedter.calendar.JYearChooser();
        jScrollPane2 = new javax.swing.JScrollPane();
        SearchDueFeesTable = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        SearchIndividualPaidTable = new javax.swing.JTable();
        jButton5 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Society Fees");

        jTabbedPane1.setBackground(new java.awt.Color(153, 153, 255));
        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Society Member Id (SMI)");

        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Society Fee - Month");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Society Fee - Year");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("Day of Payment");

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton1.setText("Ok");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton2.setText("Reset");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jDateChooser1.setDateFormatString("yyyy-MM-dd");
        jDateChooser1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jMonthChooser1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jMonthChooser1.setPreferredSize(new java.awt.Dimension(100, 20));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setText("Amount (Taka)");

        jTextField2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton4.setText("Main Menu");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton6.setText("Calculate Fee");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton4))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(552, 552, 552)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(650, 650, 650)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jMonthChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jYearChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 34, Short.MAX_VALUE)))
                                .addGap(664, 664, 664)))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(689, 689, 689))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(633, 633, 633)
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(709, 709, 709)
                        .addComponent(jButton6)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jButton4)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                            .addComponent(jMonthChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                            .addComponent(jYearChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(211, 211, 211)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addComponent(jButton6)
                .addGap(91, 91, 91)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(95, 95, 95)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(167, 167, 167))
        );

        jTabbedPane1.addTab("Collect Fees", jPanel1);

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

        jYearChooser_FeesRecord.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        search_FeesRecord.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        search_FeesRecord.setIcon(new javax.swing.ImageIcon("E:\\3.1\\Database Lab\\HSMS_Project\\test\\search_icon.png")); // NOI18N
        search_FeesRecord.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                search_FeesRecordActionPerformed(evt);
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
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jMonthChooser_FeesRecord, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jYearChooser_FeesRecord, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(search_FeesRecord)
                        .addGap(452, 452, 452)
                        .addComponent(jButton7)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton7)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jYearChooser_FeesRecord, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(search_FeesRecord, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jMonthChooser_FeesRecord, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 660, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Fees Record", jPanel2);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Month ");

        jMonthChooser2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("Year");

        jYearChooser2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        SearchDueFeesTable.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        SearchDueFeesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "SMI", "Name", "Phone1", "Phone2", "Email"
            }
        ));
        jScrollPane2.setViewportView(SearchDueFeesTable);

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon("E:\\3.1\\Database Lab\\HSMS_Project\\test\\search_icon.png")); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
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
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jMonthChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jYearChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3)
                        .addGap(451, 451, 451)
                        .addComponent(jButton8)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton8)
                    .addComponent(jButton3)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jYearChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                        .addComponent(jMonthChooser2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 660, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Search Due Fees", jPanel3);

        jPanel4.setPreferredSize(new java.awt.Dimension(1495, 600));

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

        jButton5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon("E:\\3.1\\Database Lab\\HSMS_Project\\test\\search_icon.png")); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton9.setText("Main Menu");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel11)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton5)
                        .addGap(602, 602, 602)
                        .addComponent(jButton9))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1571, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton9)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jButton5)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 660, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Search Individual Paid", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        gotoMainMenu();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

        if(jTextField4.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "SMI is not given");
            return;
        }
        int smi = Integer.valueOf(jTextField4.getText());
        String smiString = String.valueOf(smi);
        if(check_SMI(smiString)==false){
            JOptionPane.showMessageDialog(this, "Invalid SMI!");
            return;
        }
        show_fees_list_individual();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        gotoMainMenu();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        show_due_fees_list();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        gotoMainMenu();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void search_FeesRecordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search_FeesRecordActionPerformed
        // TODO add your handling code here:
        show_fees_list();
    }//GEN-LAST:event_search_FeesRecordActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        //String pattern  = "yyyy-MM-dd";
        ///DateFormat formatter = new SimpleDateFormat(pattern);
        //String dop = formatter.format(jDateChooser1.getDate());
        int total = 0;
        int month = jMonthChooser1.getMonth()+1;
        int year = jYearChooser1.getYear();
        String monthString = String.valueOf(month);
        String yearString = String.valueOf(year);

        int smi = Integer.valueOf(jTextField1.getText());
        String smiString = String.valueOf(smi);

        try {
            connection = DriverManager.getConnection(
                "jdbc:sqlserver://localhost:1433;databaseName=HSMS_Project;selectMethod=cursor", "sa", "123456");
            Statement statement = connection.createStatement();
            String query = "Select Amount, AmountCar, AmountBike from FEES_CAL where Month in (select max(Month) "
            + "from FEES_CAL where "+month+" >= Month and Year = "+year+")";
            ResultSet resultSet = statement
            .executeQuery(query);
            while (resultSet.next()) {
                int amount = Integer.valueOf(resultSet.getString("Amount"));
                int carAmount = Integer.valueOf(resultSet.getString("AmountCar"));
                int bikeAmount = Integer.valueOf(resultSet.getString("AmountBike"));
                total = amount+carAmount+bikeAmount;
            }
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
        jTextField2.setText(String.valueOf(total));

    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        gotoMainMenu();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        jTextField1.setText("");
        jMonthChooser1.setMonth(0);
        jYearChooser1.setYear(2020);
        jDateChooser1.setDate(null);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        /*Checking if SMI is given or not*//*
        if(jTextField1.getText().isEmpty() || jDateChooser1.getDate() == null){
            JOptionPane.showMessageDialog(this, "One or more fields are empty");
            return;
        }

        String pattern  = "yyyy-MM-dd";
        DateFormat formatter = new SimpleDateFormat(pattern);
        String dop = formatter.format(jDateChooser1.getDate());

        /*Ok, SMI(or something) given, now making sure it's a number*//*
        if(!isNumeric(jTextField1.getText())){
            JOptionPane.showMessageDialog(this, "Invalid SMI!");
            return;
        }
        int smi = Integer.valueOf(jTextField1.getText());
        String smiString = String.valueOf(smi);

        /*Checking if SMI is valid or not*//*
        try {
            connection = DriverManager.getConnection(
                "jdbc:sqlserver://localhost:1433;databaseName=HSMS_Project;selectMethod=cursor", "sa", "123456");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement
            .executeQuery("SELECT COUNT(*) as Count FROM SOCIETY_MEMBER WHERE SMI = "+smiString+" ");
            if (resultSet.next()) {
                if(resultSet.getString("Count").equals("0")){
                    JOptionPane.showMessageDialog(this, "Invalid SMI!");
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*Ok, valid SMI*/
/*
        int month = jMonthChooser1.getMonth()+1;
        int year = jYearChooser1.getYear();
        String monthString = String.valueOf(month);
        String yearString = String.valueOf(year);

        /*Now checking if concerned SMI has already gievn his fees in that month of that year*//*
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
                    System.out.println(resultSet.getString("Count"));
                    JOptionPane.showMessageDialog(this, "Record already exists.");
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*The query is being taken*//*
        String query;
        query = "INSERT INTO FEES (SMI, Month, Year, Amount, DOP)" +
        "values ("+smiString+", "+monthString+", "+yearString+", "+useramount+", '"+dop+"')";

        /*All is good, executing the query*//*
        try {
            Statement statement = connection.createStatement();
            statement.executeQuery(query);

        } catch (Exception e) {  e.printStackTrace(); }
        JOptionPane.showMessageDialog(this, "Record entered successfully.");
        jTextField1.setText("");
        jMonthChooser1.setMonth(0);
        jYearChooser1.setYear(2020);
        jDateChooser1.setDate(null);
    }//GEN-LAST:event_jButton1ActionPerformed

                                        

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
        gotoMainMenu();
    }                                        

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
        gotoMainMenu();
    }                                        

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
        gotoMainMenu();
    }                                        

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
        //String pattern  = "yyyy-MM-dd";      
        ///DateFormat formatter = new SimpleDateFormat(pattern);
        //String dop = formatter.format(jDateChooser1.getDate());
        int total = 0;
        int month = jMonthChooser1.getMonth()+1;
        int year = jYearChooser1.getYear();
        String monthString = String.valueOf(month);
        String yearString = String.valueOf(year);
        
        int smi = Integer.valueOf(jTextField1.getText());
        String smiString = String.valueOf(smi);
        
        try {
            connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=HSMS_Project;selectMethod=cursor", "sa", "123456");
            Statement statement = connection.createStatement();
            String query = "Select Amount, AmountCar, AmountBike from FEES_CAL where Month in (select max(Month) "
                    + "from FEES_CAL where "+month+" >= Month and Year = "+year+")";
            ResultSet resultSet = statement
                   .executeQuery(query);           
            while (resultSet.next()) {
                int amount = Integer.valueOf(resultSet.getString("Amount"));
                int carAmount = Integer.valueOf(resultSet.getString("AmountCar"));
                int bikeAmount = Integer.valueOf(resultSet.getString("AmountBike"));
                total = amount+carAmount+bikeAmount; 
                }             
            }            
        } catch (Exception e) {
            e.printStackTrace();
        }
        jTextField2.setText(String.valueOf(total));
        
        
    }                                        

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:

    }                                           

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    public void gotoMainMenu(){
        OpeningDisplay open = new OpeningDisplay();
        open.setVisible(true);
        this.setVisible(false);
    }
    /**
     * @param args the command line arguments
     *//*
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         *//*
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Fees.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Fees.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Fees.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Fees.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form *//*
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Fees().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable FeesRecordTable;
    private javax.swing.JTable SearchDueFeesTable;
    private javax.swing.JTable SearchIndividualPaidTable;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private com.toedter.calendar.JMonthChooser jMonthChooser1;
    private com.toedter.calendar.JMonthChooser jMonthChooser2;
    private com.toedter.calendar.JMonthChooser jMonthChooser_FeesRecord;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField4;
    private com.toedter.calendar.JYearChooser jYearChooser1;
    private com.toedter.calendar.JYearChooser jYearChooser2;
    private com.toedter.calendar.JYearChooser jYearChooser_FeesRecord;
    private net.sf.jtables.table.impl.MutableTableImpl mutableTableImpl1;
    private javax.swing.JButton search_FeesRecord;
    // End of variables declaration//GEN-END:variables
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
        /*Ok, SMI(or something) given, now making sure it's a number*//*
        if(!isNumeric(smiString)){
            //JOptionPane.showMessageDialog(this, "Empty or Invalid SMI!");
            count++;
            //return;
        }
        
        /*Checking if SMI is valid or not*//*
        try {
            Connection connection;
            connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=HSMS_Project;selectMethod=cursor", "sa", "123456");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement
                   .executeQuery("SELECT COUNT(*) as Count FROM SOCIETY_MEMBER WHERE SMI = "+smiString+" ");           
            if (resultSet.next()) {
                if(resultSet.getString("Count").equals("0")){
                    //
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

    /*private void set_fee() {
        try {
            Connection connection;
            connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=HSMS_Project;selectMethod=cursor", "sa", "123456");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement
                   .executeQuery("SELECT * from ADMIN");           
            while (resultSet.next()) {
                    useramount = resultSet.getString("AmountFee"); 
                    jTextField2.setText(useramount);
            }
                         
        } catch (Exception e) {
            e.printStackTrace();
        }
        jTextField2.setEditable(false);
        
    }*/
    
//}
