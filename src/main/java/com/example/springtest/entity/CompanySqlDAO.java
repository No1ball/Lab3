package com.example.springtest.entity;

import javax.persistence.*;

@MappedSuperclass
public abstract class CompanySqlDAO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    protected int id;
    @Column(name = "name")
    protected String name;
    @Column(name = "contact")
    protected String contact;

    public boolean setName(String new_name){ //prototype
        this.name = new_name;
        return true;
    }

    public boolean setId(int newId){ //prototype
        this.id = newId;
        return true;
    }

    public boolean setContact(String new_contact){ //prototype
        this.contact = new_contact;
        return true;
    }

    public String getName(){
        return this.name;
    }

    public int getId(){
        return this.id;
    }

    public String getContact(){
        return this.contact;
    }
}