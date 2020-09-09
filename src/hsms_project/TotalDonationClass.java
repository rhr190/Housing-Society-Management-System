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
public class TotalDonationClass {
    String purpose;
    int amount;

    public TotalDonationClass(String purpose, int amount) {
        this.purpose = purpose;
        this.amount = amount;
    }

    public String getPurpose() {
        return purpose;
    }

    public int getAmount() {
        return amount;
    }
    
}
