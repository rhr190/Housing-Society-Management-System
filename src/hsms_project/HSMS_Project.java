/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hsms_project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
/**
 *
 * @author Asus
 */
public class HSMS_Project {
    
    public Connection connection;
            
    public void connectDB() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=HSMS_Project;selectMethod=cursor", "sa", "123456");

            //System.out.println("DATABASE NAME IS:" + connection.getMetaData().getDatabaseProductName());

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement
                   .executeQuery("select Month from FEES where SMI = 100");
            
            
            while (resultSet.next()) {
                
                //System.out.println("Customer NAME:" + 
                   //     resultSet.getString("FirstName"));
                System.out.println(resultSet.getString("Month"));
                
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        HSMS_Project cnObj = new HSMS_Project();
        cnObj.connectDB();
    }

    
}
