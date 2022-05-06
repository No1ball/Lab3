package com.example.springtest.entity;

import javax.persistence.*;

@MappedSuperclass
public abstract class CompanySqlDAO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contractNumber")
    protected int contractId;
    @Column(name = "name")
    protected String name;
    @Column(name = "contact")
    protected String contact;

    public boolean setName(String new_name){ //prototype
        this.name = new_name;
        return true;
    }

    public boolean setContractID(int newID){ //prototype
        this.contractId = newID;
        return true;
    }

    public boolean setContact(String new_contact){ //prototype
        this.contact = new_contact;
        return true;
    }

    public String getName(){
        return this.name;
    }

    public int getContractID(){
        return this.contractId;
    }

    public String getContact(){
        return this.contact;
    }
}
