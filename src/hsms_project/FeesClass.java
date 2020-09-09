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
class FeesClass {
    private int Month, Year, Amount,SMI ;
    private String Name, DOP;

    public FeesClass(int Month, int Year, int Amount, String DOP, String Name,int SMI ) {
        this.Month = Month;
        this.Year = Year;
        this.Amount = Amount;
        this.SMI = SMI;
        this.Name = Name;
        this.DOP = DOP;
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

    public int getSMI() {
        return SMI;
    }

    public String getName() {
        return Name;
    }

    public String getDOP() {
        return DOP;
    }
    
    
}
