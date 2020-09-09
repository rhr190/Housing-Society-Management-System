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
public class TimeslotClass {
    String timeslot;
    int rent;

    public TimeslotClass(String timeslot, int rent) {
        this.timeslot = timeslot;
        this.rent = rent;
    }

    public String getTimeslot() {
        return timeslot;
    }

    public int getRent() {
        return rent;
    }
    
    
    
}
