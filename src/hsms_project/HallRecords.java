/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hsms_project;

import java.sql.Date;
import java.util.*;
/**
 *
 * @author Dorktech
 */
public class HallRecords {
    int id,smi;
    double rentfee;
    Date doe; //date of event
    String name, cont_info, occasion, timeslot;

    public HallRecords(Date doe, String name, String cont_info, String occasion, String timeslot) {
        this.doe = doe;
        this.name = name;
        this.cont_info = cont_info;
        this.occasion = occasion;
        this.timeslot = timeslot;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSmi() {
        return smi;
    }

    public void setSmi(int smi) {
        this.smi = smi;
    }

    public double getRentfee() {
        return rentfee;
    }

    public void setRentfee(double rentfee) {
        this.rentfee = rentfee;
    }

    public Date getDoe() {
        return doe;
    }

    public void setDoe(Date doe) {
        this.doe = doe;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCont_info() {
        return cont_info;
    }

    public void setCont_info(String cont_info) {
        this.cont_info = cont_info;
    }

    public String getOccasion() {
        return occasion;
    }

    public void setOccasion(String occasion) {
        this.occasion = occasion;
    }

    public String getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(String timeslot) {
        this.timeslot = timeslot;
    }
    
    
}
