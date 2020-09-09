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
public class DonationClass {
    String Name, DOP, PurposeName;
    int amount;

    public DonationClass(String Name, int amount, String PurposeName, String DOP) {
        this.Name = Name;
        this.DOP = DOP;
        this.amount = amount;
        this.PurposeName = PurposeName;
    }

    public String getName() {
        return Name;
    }

    public String getDOP() {
        return DOP;
    }

    public int getAmount() {
        return amount;
    }
    public String getPurposeName() {
        return PurposeName;
    }
    
}
