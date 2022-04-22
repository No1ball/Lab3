package com.example.springtest.DAO;

import java.util.ArrayList;
import com.example.springtest.DAO.ContractsDAO;
public class CompanyDAO {
    protected String Name;
    protected String Contact;

    public boolean SetName(String new_name){ //prototype
        this.Name = new_name;
        return true;
    }

    public boolean SetContact(String new_contact){ //prototype
        this.Contact = new_contact;
        return true;
    }

    public String GetName(){
        return this.Name;
    }

    public String GetContact(){
        return this.Contact;
    }
}

class ClientDAO extends CompanyDAO {
    private String ContractId;
    private int TotalSumm = 0;
    private ArrayList<ContractsDAO> OldContractID;

    public boolean SetContractID(String newID){ //prototype
        this.ContractId = newID;
        return true;
    }

    public boolean SetTotalSumm(int new_summ){ //prototype
        this.TotalSumm = new_summ;
        return true;
    }

    public boolean SetOldContractID(ArrayList<ContractsDAO> new_oldcntrid){ //prototype
        this.OldContractID = new_oldcntrid;
        return true;
    }

    public String GetContractID(){
        return this.ContractId;
    }

    public int GetTotalSumm(){
        return this.TotalSumm;
    }

    public ArrayList <ContractsDAO> GetOldCntrctID(){
        return this.OldContractID;
    }
}

