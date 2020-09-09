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
public class TotalFeesClass {
    int Month, Year, Total;
    
    public TotalFeesClass(int Month, int Year, int Total){
        this.Month = Month;
        this.Year = Year;
        this.Total = Total;
    }
    
    public int getMonth() {
        return Month;
    }

    public int getYear() {
        return Year;
    }

    public int getTotal() {
        return Total;
    }
}
