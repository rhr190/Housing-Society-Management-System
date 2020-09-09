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
public class FlatClass {
    private String fnum, name, contact;
    private int smi;
    
    public FlatClass(String fnum, String name, String contact, int smi) {
        this.fnum = fnum;
        this.name = name;
        this.contact = contact;
        this.smi = smi;
        
    }

    public String getFnum() {
        return fnum;
    }

    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }

    public int getSMI() {
        return smi;
    }
}
