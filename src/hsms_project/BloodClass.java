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
class BloodClass {
    String BG, Name, Phone1, Phone2, Email, Address;

    public BloodClass(String BG, String Name, String Phone1, String Phone2, String Email, String Address) {
        this.BG = BG;
        this.Name = Name;
        this.Phone1 = Phone1;
        this.Phone2 = Phone2;
        this.Email = Email;
        this.Address = Address;
    }

    public String getBG() {
        return BG;
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

    public String getAddress() {
        return Address;
    }
    
}
