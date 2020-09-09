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
public class ComplaintClass {
    int indx, smi;
    String sub, descrip, comm, date;

    public ComplaintClass(int indx, int smi, String sub, String descrip, String comm, String date) {
        this.indx = indx;
        this.smi = smi;
        this.sub = sub;
        this.descrip = descrip;
        this.comm = comm;
        this.date = date;
    }

    public int getIndx() {
        return indx;
    }

    public int getSmi() {
        return smi;
    }

    public String getSub() {
        return sub;
    }

    public String getDescrip() {
        return descrip;
    }

    public String getComm() {
        return comm;
    }

    public String getDate() {
        return date;
    }
    
}
