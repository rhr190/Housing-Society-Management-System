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
public class FeesRecordClass {
    int Month, Year, BasicFee, CarFee, BikeFee;
    String admin;

    public FeesRecordClass(int Month, int Year, int BasicFee, int CarFee, int BikeFee, String admin) {
        this.Month = Month;
        this.Year = Year;
        this.BasicFee = BasicFee;
        this.CarFee = CarFee;
        this.BikeFee = BikeFee;
        this.admin = admin;
    }

    public int getMonth() {
        return Month;
    }

    public int getYear() {
        return Year;
    }

    public int getBasicFee() {
        return BasicFee;
    }

    public int getCarFee() {
        return CarFee;
    }

    public int getBikeFee() {
        return BikeFee;
    }

    public String getAdmin() {
        return admin;
    }
    
    
}
