/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hsms_project;

import java.awt.Button;

/**
 *
 * @author Asus
 */
class VClass {
    String Type, Number, Model, Name, Phone1, Address;
    int SMI;
    private Button button;

    public VClass(String Type, String Number, String Model,int SMI, String Name, String Phone1) {
        this.Type = Type;
        this.Number = Number;
        this.Model = Model;
        this.Name = Name;
        this.Phone1 = Phone1;
        this.SMI = SMI;
    }

    public String getType() {
        return Type;
    }

    public String getNumber() {
        return Number;
    }

    public String getModel() {
        return Model;
    }

    public String getName() {
        return Name;
    }

    public String getPhone1() {
        return Phone1;
    }

    

    public int getSMI() {
        return SMI;
    }

   

    
    
    
    
}
