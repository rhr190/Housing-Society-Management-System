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
public class CompRecord {
    int index, comp_smi;
    String comp_name, comp_title, comp_descrip, comp_status;
    Date comp_date;

    public CompRecord(int index, int comp_smi, String comp_title, String comp_descrip, String comp_status, Date comp_date) {
        this.index = index;
        this.comp_smi = comp_smi;
        this.comp_name = comp_name;
        this.comp_title = comp_title;
        this.comp_descrip = comp_descrip;
        this.comp_status = comp_status;
        this.comp_date = comp_date;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getComp_smi() {
        return comp_smi;
    }

    public void setComp_smi(int comp_smi) {
        this.comp_smi = comp_smi;
    }

    public String getComp_name() {
        return comp_name;
    }

    public void setComp_name(String comp_name) {
        this.comp_name = comp_name;
    }

    public String getComp_title() {
        return comp_title;
    }

    public void setComp_title(String comp_title) {
        this.comp_title = comp_title;
    }

    public String getComp_descrip() {
        return comp_descrip;
    }

    public void setComp_descrip(String comp_descrip) {
        this.comp_descrip = comp_descrip;
    }

    public String getComp_status() {
        return comp_status;
    }

    public void setComp_status(String comp_status) {
        this.comp_status = comp_status;
    }

    public Date getComp_date() {
        return comp_date;
    }

    public void setComp_date(Date comp_date) {
        this.comp_date = comp_date;
    }
    
    
    

   
    
    
}
