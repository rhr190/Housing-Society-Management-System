/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hsms_project;

/**
 *
 * @author Asus
 */
public class DueFeesClass {
    int SMI;
    String Name, Phone1, Phone2, Email;

    public DueFeesClass(int SMI, String Name, String Phone1, String Phone2, String Email) {
        this.SMI = SMI;
        this.Name = Name;
        this.Phone1 = Phone1;
        this.Phone2 = Phone2;
        this.Email = Email;
    }

    public int getSMI() {
        return SMI;
    }

    public String getName() {
        return Name;
    }

    public String getPhone1() {
        return Phone1;
    }

    public String getPhone2() {
        return Phone2;
    }

    public String getEmail() {
        return Email;
    }
    
            
                    
                    
}
