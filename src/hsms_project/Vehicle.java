/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hsms_project;

import static hsms_project.SocietyFees.isNumeric;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author Asus
 */
public class Vehicle extends javax.swing.JFrame {
    public Connection connection;
    /**
     * Creates new form Vehicle
     */
    public Vehicle() {
        initComponents();
        jPopupMenu1.add(jPanel6);
        jPopupMenu2.add(jPanel7);
    }
    
    //Showing Car or Bike per selected in the combo-box
    public ArrayList<VClass> vtypeList(){
        ArrayList<VClass> vtypeList= new ArrayList<>();
        
        ResultSet resultset;
        try{
            connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=HSMS_Project;selectMethod=cursor", "sa", "123456");
            Statement statement = connection.createStatement();
            
            String query;
            if(jComboBox1.getSelectedIndex() == 0){
                query = "Select VEHICLE.Type, VEHICLE.Number, VEHICLE.Model, SOCIETY_MEMBER.SMI, SOCIETY_MEMBER.Name, "
                    + "SOCIETY_MEMBER.Phone1, SOCIETY_MEMBER.F_Num  from " +
                    "SOCIETY_MEMBER inner join VEHICLE on SOCIETY_MEMBER.SMI = VEHICLE.SMI where Type = 'Car'";
                
            }
            else{
                query = "Select VEHICLE.Type, VEHICLE.Number, VEHICLE.Model, SOCIETY_MEMBER.SMI, SOCIETY_MEMBER.Name, "
                    + "SOCIETY_MEMBER.Phone1, SOCIETY_MEMBER.F_Num  from " +
                    "SOCIETY_MEMBER inner join VEHICLE on SOCIETY_MEMBER.SMI = VEHICLE.SMI where Type = 'Bike'";
                
            }
            resultset = statement.executeQuery(query);
            VClass vtypeclass;
            while(resultset.next()){
                vtypeclass = new VClass(resultset.getString("Type"),resultset.getString("Number"),resultset.getString("Model"), resultset.getInt("SMI"), resultset.getString("Name"), resultset.getString("Phone1"));
                vtypeList.add(vtypeclass);

            }    
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return vtypeList;
    }
    public void show_vtype_List(){
        ArrayList<VClass> vt = vtypeList();
        DefaultTableModel model = (DefaultTableModel)VehicleTable.getModel();
        model.setRowCount(0);
        Object[] row = new Object[6];
        for(int i=0; i<vt.size(); i++){
            row[0] = vt.get(i).getType();
            row[1] = vt.get(i).getNumber();
            row[2] = vt.get(i).getModel();
            row[3] = vt.get(i).getSMI();
            row[4] = vt.get(i).getName();
            row[5] = vt.get(i).getPhone1();
            //row[6] = vt.get(i).getAddress();
            //row[7] = vt.get(i).getButton();
            model.addRow(row);
            
        }
    }
    
    //showing vehicles per license plate number
    public ArrayList<VClass> vnumList(){
        ArrayList<VClass> vnumList= new ArrayList<>();
        
        ResultSet resultset;
        try{
            connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=HSMS_Project;selectMethod=cursor", "sa", "123456");
            Statement statement = connection.createStatement();
            
            String num_string = jTextField8.getText().toString();
            System.out.println(num_string);
            String query;
            query = "SELECT VEHICLE.Type, VEHICLE.Number, VEHICLE.Model, SOCIETY_MEMBER.SMI, SOCIETY_MEMBER.Name, "
                + "SOCIETY_MEMBER.Phone1 from " +
                "SOCIETY_MEMBER INNER JOIN VEHICLE on SOCIETY_MEMBER.SMI = VEHICLE.SMI where VEHICLE.Number LIKE '%"+num_string+"%' and SOCIETY_MEMBER.State=1";
            resultset = statement.executeQuery(query);
            VClass vtypeclass;
            while(resultset.next()){
                    vtypeclass = new VClass(resultset.getString("Type"),resultset.getString("Number"),resultset.getString("Model"), resultset.getInt("SMI"), resultset.getString("Name"), resultset.getString("Phone1"));
                    vnumList.add(vtypeclass);
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return vnumList;
    }
    public void show_vnum_List(){
        ArrayList<VClass> vt = vnumList();
        DefaultTableModel model = (DefaultTableModel)VehicleTable.getModel();
        model.setRowCount(0);
        Object[] row = new Object[6];
        for(int i=0; i<vt.size(); i++){
            row[0] = vt.get(i).getType();
            row[1] = vt.get(i).getNumber();
            row[2] = vt.get(i).getModel();
            row[3] = vt.get(i).getSMI();
            row[4] = vt.get(i).getName();
            row[5] = vt.get(i).getPhone1();
           // row[6] = vt.get(i).getAddress();
            model.addRow(row);
            
        }
    }
    
    //showing vehicles per model name search
    public ArrayList<VClass> vmodelList(){
        ArrayList<VClass> vmodelList= new ArrayList<>();
        String query;
        ResultSet resultset;
        try{
            connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=HSMS_Project;selectMethod=cursor", "sa", "123456");
            Statement statement = connection.createStatement();
            
            String model_string = jTextField5.getText();//.toUpperCase();
            query = "Select VEHICLE.Type, VEHICLE.Number, VEHICLE.Model, SOCIETY_MEMBER.SMI, SOCIETY_MEMBER.Name, "
                + "SOCIETY_MEMBER.Phone1 from " +
                "SOCIETY_MEMBER inner join VEHICLE on SOCIETY_MEMBER.SMI = VEHICLE.SMI where VEHICLE.Model LIKE '%"+model_string+"%' and SOCIETY_MEMBER.State=1";
            resultset = statement.executeQuery(query);
            VClass vtypeclass;
            while(resultset.next()){
                vtypeclass = new VClass(resultset.getString("Type"),resultset.getString("Number"),resultset.getString("Model"), resultset.getInt("SMI"), resultset.getString("Name"), resultset.getString("Phone1"));
                vmodelList.add(vtypeclass);
            }    
        }catch(Exception e){
            e.printStackTrace();
        }
        return vmodelList;
    }
    public void show_model_List(){
        ArrayList<VClass> vt = vmodelList();
        DefaultTableModel model = (DefaultTableModel)VehicleTable.getModel();
        model.setRowCount(0);
        Object[] row = new Object[6];
        for(int i=0; i<vt.size(); i++){
            row[0] = vt.get(i).getType();
            row[1] = vt.get(i).getNumber();
            row[2] = vt.get(i).getModel();
            row[3] = vt.get(i).getSMI();
            row[4] = vt.get(i).getName();
            row[5] = vt.get(i).getPhone1();
            //row[6] = vt.get(i).getAddress();
            model.addRow(row);
            
        }
    }
    
    //showing vehicles per smi number search
    public ArrayList<VClass> vsmilList(){
        ArrayList<VClass> vsmilList= new ArrayList<>();
        String query;
        ResultSet resultset;
        try{
            connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=HSMS_Project;selectMethod=cursor", "sa", "123456");
            Statement statement = connection.createStatement();
            
            String smi_string = jTextField6.getText();//.toUpperCase();
            query = "Select VEHICLE.Type, VEHICLE.Number, VEHICLE.Model, SOCIETY_MEMBER.SMI, SOCIETY_MEMBER.Name, "
                + "SOCIETY_MEMBER.Phone1, SOCIETY_MEMBER.F_Num  from " +
                "SOCIETY_MEMBER inner join VEHICLE on SOCIETY_MEMBER.SMI = VEHICLE.SMI where VEHICLE.SMI = "+smi_string+" and SOCIETY_MEMBER.State=1";
            resultset = statement.executeQuery(query);
            VClass vtypeclass;
            while(resultset.next()){
                vtypeclass = new VClass(resultset.getString("Type"),resultset.getString("Number"),resultset.getString("Model"), resultset.getInt("SMI"), resultset.getString("Name"), resultset.getString("Phone1"));
                vsmilList.add(vtypeclass);
            }    
        }catch(Exception e){
            e.printStackTrace();
        }
        return vsmilList;
    }
    public void show_smi_List(){
        ArrayList<VClass> vt = vsmilList();
        DefaultTableModel model = (DefaultTableModel)VehicleTable.getModel();
        model.setRowCount(0);
        Object[] row = new Object[6];
        for(int i=0; i<vt.size(); i++){
            row[0] = vt.get(i).getType();
            row[1] = vt.get(i).getNumber();
            row[2] = vt.get(i).getModel();
            row[3] = vt.get(i).getSMI();
            row[4] = vt.get(i).getName();
            row[5] = vt.get(i).getPhone1();
            //row[6] = vt.get(i).getAddress();
            
            model.addRow(row);
            
        }
    }
    
    //showing vehicles per society member name search
    public ArrayList<VClass> vnamelList(){
        ArrayList<VClass> vnamelList= new ArrayList<>();
        String query;
        ResultSet resultset;
        try{
            connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=HSMS_Project;selectMethod=cursor", "sa", "123456");
            Statement statement = connection.createStatement();
            
            //String name_string = String.valueOf(jComboBox3.getSelectedItem());//.toUpperCase();
            //System.out.println(name_string);
            String name_string = jTextField7.getText().toString();
            query = "Select VEHICLE.Type, VEHICLE.Number, VEHICLE.Model, SOCIETY_MEMBER.SMI, SOCIETY_MEMBER.Name, "
                + "SOCIETY_MEMBER.Phone1, SOCIETY_MEMBER.F_Num  from " +
                "SOCIETY_MEMBER inner join VEHICLE on SOCIETY_MEMBER.SMI = VEHICLE.SMI where SOCIETY_MEMBER.Name = '"+name_string+"'";
            resultset = statement.executeQuery(query);
            VClass vnameclass;
            while(resultset.next()){
                vnameclass = new VClass(resultset.getString("Type"),resultset.getString("Number"),resultset.getString("Model"), resultset.getInt("SMI"), resultset.getString("Name"), resultset.getString("Phone1"));
                vnamelList.add(vnameclass);
            }    
        }catch(Exception e){
            e.printStackTrace();
        }
        return vnamelList;
    }
    public void show_vname_List(){
        ArrayList<VClass> vt = vnamelList();
        DefaultTableModel model = (DefaultTableModel)VehicleTable.getModel();
        model.setRowCount(0);
        Object[] row = new Object[6];
        for(int i=0; i<vt.size(); i++){
            row[0] = vt.get(i).getType();
            row[1] = vt.get(i).getNumber();
            row[2] = vt.get(i).getModel();
            row[3] = vt.get(i).getSMI();
            row[4] = vt.get(i).getName();
            row[5] = vt.get(i).getPhone1();
            //row[6] = vt.get(i).getAddress();
            //row[7] = vt.get(i).getButton();
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList<>();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jRadioButton_car = new javax.swing.JRadioButton();
        jRadioButton_bike = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        registerVehicleBtn = new javax.swing.JButton();
        resetBtn = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jButton9 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jMonthChooser1 = new com.toedter.calendar.JMonthChooser();
        jYearChooser1 = new com.toedter.calendar.JYearChooser();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        VehicleTable = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        numberSearchBtn = new javax.swing.JButton();
        modelSearchBtn = new javax.swing.JButton();
        jTextField5 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        smiSearchBtn = new javax.swing.JButton();
        jTextField6 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        typeSearchBtn = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        removeVehicleBtn = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        nameSearchBtn = new javax.swing.JButton();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();

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

        jList2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jScrollPane7.setViewportView(jList2);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
        );

        jPopupMenu2.setFocusable(false);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Society Vehicle Management");

        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Vehicle Type*");

        buttonGroup1.add(jRadioButton_car);
        jRadioButton_car.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jRadioButton_car.setText("Car");
        jRadioButton_car.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_carActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButton_bike);
        jRadioButton_bike.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jRadioButton_bike.setText("Bike");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Society Member Id (SMI)*");

        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Number Plate*");

        jTextField2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Model");

        jTextField3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        registerVehicleBtn.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        registerVehicleBtn.setText("Ok");
        registerVehicleBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerVehicleBtnActionPerformed(evt);
            }
        });

        resetBtn.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        resetBtn.setText("Reset");
        resetBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetBtnActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("* marked fields must be filled");

        jButton9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton9.setText("Main Menu");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setText("Entry Month");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel12.setText("Entry Year");

        jMonthChooser1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton9)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(836, 836, 836))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(551, 551, 551)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1)
                                .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jRadioButton_car)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jRadioButton_bike))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jMonthChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jYearChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(resetBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(39, 39, 39))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(1, 1, 1)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jTextField2)
                                        .addComponent(jTextField1)
                                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(670, 670, 670)
                        .addComponent(registerVehicleBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(640, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(692, 692, 692)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jButton9)
                .addGap(83, 83, 83)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadioButton_car)
                    .addComponent(jRadioButton_bike))
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jMonthChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jYearChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(88, 88, 88)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(registerVehicleBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(resetBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(127, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Register Vehicle", jPanel1);

        VehicleTable.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        VehicleTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Type", "Number", "Model", "SMI", "Name", "Phone1"
            }
        ));
        jScrollPane1.setViewportView(VehicleTable);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("Number");

        numberSearchBtn.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        numberSearchBtn.setIcon(new javax.swing.ImageIcon("E:\\3.1\\Database Lab\\HSMS_Project\\test\\search_icon.png")); // NOI18N
        numberSearchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numberSearchBtnActionPerformed(evt);
            }
        });

        modelSearchBtn.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        modelSearchBtn.setIcon(new javax.swing.ImageIcon("E:\\3.1\\Database Lab\\HSMS_Project\\test\\search_icon.png")); // NOI18N
        modelSearchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modelSearchBtnActionPerformed(evt);
            }
        });

        jTextField5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("Model");

        smiSearchBtn.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        smiSearchBtn.setIcon(new javax.swing.ImageIcon("E:\\3.1\\Database Lab\\HSMS_Project\\test\\search_icon.png")); // NOI18N
        smiSearchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                smiSearchBtnActionPerformed(evt);
            }
        });

        jTextField6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField6ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setText("SMI");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setText("Type");

        jComboBox1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Car", "Bike" }));

        typeSearchBtn.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        typeSearchBtn.setIcon(new javax.swing.ImageIcon("E:\\3.1\\Database Lab\\HSMS_Project\\test\\search_icon.png")); // NOI18N
        typeSearchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                typeSearchBtnActionPerformed(evt);
            }
        });

        jButton10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton10.setText("Main Menu");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        removeVehicleBtn.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        removeVehicleBtn.setForeground(new java.awt.Color(204, 0, 51));
        removeVehicleBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons_database/icons8-remove-64.png"))); // NOI18N
        removeVehicleBtn.setText("Remove Selected Vehicle");
        removeVehicleBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeVehicleBtnActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setText("Name");

        nameSearchBtn.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        nameSearchBtn.setIcon(new javax.swing.ImageIcon("E:\\3.1\\Database Lab\\HSMS_Project\\test\\search_icon.png")); // NOI18N
        nameSearchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameSearchBtnActionPerformed(evt);
            }
        });

        jTextField7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField7ActionPerformed(evt);
            }
        });
        jTextField7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField7KeyReleased(evt);
            }
        });

        jTextField8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField8ActionPerformed(evt);
            }
        });
        jTextField8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField8KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(typeSearchBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(numberSearchBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(modelSearchBtn)
                                .addGap(68, 68, 68)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(smiSearchBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nameSearchBtn)
                                .addGap(31, 31, 31)
                                .addComponent(jButton10))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(removeVehicleBtn)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(numberSearchBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(typeSearchBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8)
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(nameSearchBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jButton10)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jTextField5, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(modelSearchBtn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextField8, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextField6, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(smiSearchBtn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(8, 8, 8)
                .addComponent(removeVehicleBtn)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 660, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Search Vehicle", jPanel2);

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

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed
      
    
    private void jRadioButton_carActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_carActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton_carActionPerformed

    private void registerVehicleBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerVehicleBtnActionPerformed
        //Checking if * fields are empty or not
        
        if((!jRadioButton_car.isSelected() && !jRadioButton_bike.isSelected()) || jTextField1.getText().isEmpty() || jTextField2.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "One or more fields are empty");
            
            return;
        }
        
        /*Ok, SMI(or something) given, now making sure it's a number*/
        if(!isNumeric(jTextField1.getText())){
            JOptionPane.showMessageDialog(this, "Invalid SMI!");
            return;
        }
        String smiString = jTextField1.getText();
        String  number, model;
        number = jTextField2.getText(); //so that all entries are upper-case maintained
        model = jTextField3.getText().toString();
        int month = jMonthChooser1.getMonth()+1;
        int year = jYearChooser1.getYear();
        String stringMonth = String.valueOf(month);
        String stringYear = String.valueOf(year);
        /*Checking if SMI is valid or not*/
        try {
  
            connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=HSMS_Project;selectMethod=cursor", "sa", "123456");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement
                   .executeQuery("SELECT COUNT(*) as Count FROM SOCIETY_MEMBER WHERE SMI = "+smiString+" and State=1");           
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
        
        /*Now checking if concerned Vehicle has already been registered*/
        try {
                connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=HSMS_Project;selectMethod=cursor", "sa", "123456");
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement
                       .executeQuery("SELECT COUNT(*) as Count FROM VEHICLE WHERE Number = '"+number+"'");           
                if (resultSet.next()) {
                    if(!resultSet.getString("Count").equals("0")){
                        //System.out.println(resultSet.getString("Count"));
                        JOptionPane.showMessageDialog(this, "Vehicle already registered.");
                        return;
                    }             
                }                
        } catch (Exception e) {
            e.printStackTrace();
        }
        String query;
        
        /*All are good, executing the query*/
        try {
           
                if(jRadioButton_car.isSelected()){
                query = "INSERT INTO VEHICLE(SMI, Type, Number, Model, EntryMonth, EntryYear) "
                        + "VALUES("+smiString+", 'Car','"+number+"','"+model+"', "+stringMonth+", "+stringYear+")";
                }else{
                    query = "INSERT INTO VEHICLE(SMI, Type, Number, Model, EntryMonth, EntryYear) "
                            + "VALUES("+smiString+", 'Bike','"+number+"','"+model+"', "+stringMonth+", "+stringYear+")";
                }
                Statement statement = connection.createStatement();
                statement.executeUpdate(query);
                
        } catch (Exception ex) {  //Logger.getLogger(Fees.class.getName()).log(Level.SEVERE, null, ex); }
            ex.printStackTrace();;
        }
        JOptionPane.showMessageDialog(this, "Vehicle record entered successfully.");
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        jRadioButton_car.setSelected(false);
        jRadioButton_bike.setSelected(false);
    }//GEN-LAST:event_registerVehicleBtnActionPerformed

    private void resetBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetBtnActionPerformed
        // TODO add your handling code here:
            jTextField1.setText("");
            jTextField2.setText("");
            jTextField3.setText("");
            jRadioButton_car.setSelected(false);
            jRadioButton_bike.setSelected(false);
    }//GEN-LAST:event_resetBtnActionPerformed

    private void modelSearchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modelSearchBtnActionPerformed
        // TODO add your handling code here:
        if(jTextField5.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Field is empty!");
            return;
        }
        show_model_List();
        jTextField6.setText("");
        jTextField8.setText("");
    }//GEN-LAST:event_modelSearchBtnActionPerformed

    private void removeVehicleBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeVehicleBtnActionPerformed
        // TODO add your handling code here:
        if(VehicleTable.getSelectionModel().isSelectionEmpty()){
            JOptionPane.showMessageDialog(this, "No vehicle selected.");
            return;
        }
        

        TableModel model = VehicleTable.getModel();
        int[] selectedrows = VehicleTable.getSelectedRows();
        for (int i = 0; i < selectedrows.length; i++){
                String number = VehicleTable.getValueAt(selectedrows[i], 1).toString();
                try {
                    connection = DriverManager.getConnection(
                            "jdbc:sqlserver://localhost:1433;databaseName=HSMS_Project;selectMethod=cursor", "sa", "123456");
                    Statement statement = connection.createStatement();
                    statement.executeUpdate("DELETE FROM VEHICLE WHERE Number = '"+number+"'"); 

                } catch (Exception e) {
                        e.printStackTrace();
                }
        }

        JOptionPane.showMessageDialog(this, "Vehicle removed successfully.");
        show_vtype_List();
    }//GEN-LAST:event_removeVehicleBtnActionPerformed

    private void typeSearchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_typeSearchBtnActionPerformed
        // TODO add your handling code here:
        show_vtype_List();
    }//GEN-LAST:event_typeSearchBtnActionPerformed

    private void numberSearchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numberSearchBtnActionPerformed
        // TODO add your handling code here:
       /* if(jTextField4.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Field is empty!");
            return;
        }*/
        show_vnum_List();
        jTextField6.setText("");
        jTextField5.setText("");
        
    }//GEN-LAST:event_numberSearchBtnActionPerformed

    private void smiSearchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_smiSearchBtnActionPerformed
        
        /*Ok, SMI(or something) given, now making sure it's valid*/
        String smiString = jTextField6.getText();
        if(check_SMI(smiString)==false){
            JOptionPane.showMessageDialog(this, "Invalid SMI!");
            return;
        }

        show_smi_List();
        jTextField5.setText("");
        jTextField8.setText("");
    }//GEN-LAST:event_smiSearchBtnActionPerformed

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField6ActionPerformed

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        // TODO add your handling code here:
        jTextField6.setText("");
        jTextField8.setText("");
    }//GEN-LAST:event_jTextField5ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        gotoMainMenu();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        gotoMainMenu();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void nameSearchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameSearchBtnActionPerformed
        // TODO add your handling code here:
        show_vname_List();
    }//GEN-LAST:event_nameSearchBtnActionPerformed

    private void jTextField7KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField7KeyReleased
        // TODO add your handling code here:
        DefaultListModel model = new DefaultListModel();
        jList1.setModel(model);    
        try{
            connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=HSMS_Project;selectMethod=cursor", "sa", "123456");
            Statement statement = connection.createStatement();
            //ResultSet resultSet = statement.executeQuery("select SOCIETY_MEMBER.Name from SOCIETY_MEMBER"); 
            
            String search = jTextField7.getText().trim();

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

    private void jTextField7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField7ActionPerformed

    private void jTextField8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField8ActionPerformed

    private void jTextField8KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField8KeyReleased
        // TODO add your handling code here:
        DefaultListModel model = new DefaultListModel();
        jList2.setModel(model);    
        try{
            connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=HSMS_Project;selectMethod=cursor", "sa", "123456");
            Statement statement = connection.createStatement();
            //ResultSet resultSet = statement.executeQuery("select SOCIETY_MEMBER.Name from SOCIETY_MEMBER"); 
            
            String search = jTextField8.getText().trim();

            model.removeAllElements();
            if(!search.equals("")){
                ResultSet resultSet = statement.executeQuery("select Number from VEHICLE where Number like '%"+search+"%'"); 
                while(resultSet.next()){
                    String name = resultSet.getString("Number");
                    model.addElement(name);
                }
            }
            
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        jPopupMenu2.show(jTextField8, 0, jTextField8.getHeight());
        jList2.addMouseListener(new MouseAdapter() {
         public void mouseClicked(MouseEvent me) {
            jPopupMenu2.setVisible(false);
            if (me.getClickCount() == 1) {
               JList target = (JList)me.getSource();
               int index = target.locationToIndex(me.getPoint());
               if (index >= 0) {
                  Object item = target.getModel().getElementAt(index);
                  jTextField8.setText(item.toString());
               }
            }
         }
      });
    }//GEN-LAST:event_jTextField8KeyReleased
    
    public static boolean isNumeric(String str) { 
      try {  
        Integer.parseInt(str);  
        return true;
      } catch(NumberFormatException e){  
        return false;  
      }  
    }
    public void gotoMainMenu(){
        OpeningDisplay open = new OpeningDisplay();
        open.setVisible(true);
        this.setVisible(false);
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
                   .executeQuery("SELECT COUNT(*) as Count FROM SOCIETY_MEMBER WHERE SMI = "+smiString+" ");           
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
    
   /* public void fill_up_name_combo(){
        AutoCompleteDecorator.decorate(jComboBox3);
        jComboBox3.addItem("");
        try {
            connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=HSMS_Project;selectMethod=cursor", "sa", "123456");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select SOCIETY_MEMBER.Name from VEHICLE inner join SOCIETY_MEMBER "
                    + "on VEHICLE.SMI = SOCIETY_MEMBER.SMI"); 
            while(resultSet.next()){
                String name = resultSet.getString("Name");
                jComboBox3.addItem(name);
            }
        } catch (Exception e) {
                e.printStackTrace();
        }
    }*/
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
            java.util.logging.Logger.getLogger(Vehicle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Vehicle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Vehicle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Vehicle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Vehicle().setVisible(true);
            }
        });
        
       
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable VehicleTable;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList<String> jList1;
    private javax.swing.JList<String> jList2;
    private com.toedter.calendar.JMonthChooser jMonthChooser1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu2;
    private javax.swing.JRadioButton jRadioButton_bike;
    private javax.swing.JRadioButton jRadioButton_car;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private com.toedter.calendar.JYearChooser jYearChooser1;
    private javax.swing.JButton modelSearchBtn;
    private javax.swing.JButton nameSearchBtn;
    private javax.swing.JButton numberSearchBtn;
    private javax.swing.JButton registerVehicleBtn;
    private javax.swing.JButton removeVehicleBtn;
    private javax.swing.JButton resetBtn;
    private javax.swing.JButton smiSearchBtn;
    private javax.swing.JButton typeSearchBtn;
    // End of variables declaration//GEN-END:variables
}
