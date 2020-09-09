package hsms_project;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Personal-PC
 */
class UserInfo {
    private int SMI;
    private String Name, Phone1, Phone2, Email,  DOB, BG, NID, F_Num,  P_Add, Occupation, Sub_Name, Sub_Phone, Sub_Email;
    
    public UserInfo(int SMI, String Name, String Phone1, String Phone2, String Email, String DOB, String BG,  String NID, String F_Num,  String P_Add, String Occupation, String Sub_Name, String Sub_Phone, String Sub_Email){
        this.SMI=SMI;
        this.Name = Name;
        this.Phone1=Phone1;
        this.Phone2=Phone2;
        this.Email=Email;
        this.DOB = DOB;
        this.BG = BG;
        this.NID= NID;
        this.F_Num=F_Num;
        this.P_Add= P_Add;
        this.Occupation= Occupation;
        this.Sub_Name= Sub_Name;
        this.Sub_Phone=Sub_Phone;
        this.Sub_Email = Sub_Email;
}

    public int getSMI() {
        return SMI;
    }

    public String getName() {
        return Name;
    }

    public String getPhone1() {
        return Phone1;
    }

    public String getPhone2() {
        return Phone2;
    }

    public String getEmail() {
        return Email;
    }

    public String getDOB() {
        return DOB;
    }

    public String getBG() {
        return BG;
    }

    public String getNID() {
        return NID;
    }

    public String getF_Num() {
        return F_Num;
    }

    public String getP_Add() {
        return P_Add;
    }

    public String getOccupation() {
        return Occupation;
    }

    public String getSub_Name() {
        return Sub_Name;
    }

    public String getSub_Phone() {
        return Sub_Phone;
    }

    public String getSub_Email() {
        return Sub_Email;
    }

    
}
