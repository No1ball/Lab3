package com.example.springtest.entity;

import javax.persistence.*;

@MappedSuperclass
public class CompanySqlDAO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contractNumber")
    private int ContractId;
    @Column(name = "name")
    protected String Name;
    @Column(name = "contact")
    protected String Contact;

    public boolean setName(String new_name){ //prototype
        this.Name = new_name;
        return true;
    }

    public boolean setContractID(int newID){ //prototype
        this.ContractId = newID;
        return true;
    }

    public boolean setContact(String new_contact){ //prototype
        this.Contact = new_contact;
        return true;
    }

    public String getName(){
        return this.Name;
    }

    public int getContractID(){
        return this.ContractId;
    }

    public String getContact(){
        return this.Contact;
    }
}
