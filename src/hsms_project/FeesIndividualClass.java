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
public class FeesIndividualClass {
    String Name, DOP;
    int SMI, Month, Year, Amount;

    public FeesIndividualClass(int SMI, String Name, String DOP,  int Month, int Year, int Amount) {
        this.Name = Name;
        this.DOP = DOP;
        this.SMI = SMI;
        this.Month = Month;
        this.Year = Year;
        this.Amount = Amount;
    }

    public String getName() {
        return Name;
    }

    public String getDOP() {
        return DOP;
    }

    public int getSMI() {
        return SMI;
    }

    public int getMonth() {
        return Month;
    }

    public int getYear() {
        return Year;
    }

    public int getAmount() {
        return Amount;
    }
    
                            
}
