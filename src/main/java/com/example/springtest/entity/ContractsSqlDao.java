package com.example.springtest.entity;

import com.example.springtest.DAO.DevicesDAO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="contracts")
public class ContractsSqlDao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;
    @Column(name="name")
    private String CompName;
    @Column(name = "relevance")
    private boolean relevance;
    @Column(name = "fDate")
    private String fDate;
    @Column(name = "lDate")
    private String lDate;
    @Column(name = "price")
    private int price;
    @ManyToMany(targetEntity = DevicesSqlDao.class)
    @JoinTable (name="equipments",
            joinColumns=@JoinColumn (name="contractid"),
            inverseJoinColumns=@JoinColumn(name="count"))
    private List<DevicesSqlDao> equipments;


    public boolean setID(int new_id){ //prototype
        this.id = new_id;
        return true;
    }

    public boolean setCompName(String new_CompName){ //prototype
        this.CompName = new_CompName;
        return true;
    }

    public boolean setRelevance(boolean new_relevance){ //prototype
        this.relevance = new_relevance;
        return true;
    }

    public boolean setFDate(String new_fDate){ //prototype
        this.fDate = new_fDate;
        return true;
    }

    public boolean setLDate(String new_lDate){ //prototype
        this.lDate = new_lDate;
        return true;
    }

    public boolean setPrice(int new_price){ //prototype
        this.price = new_price;
        return true;
    }

    public boolean setEquipments(List <DevicesSqlDao> new_device){ //prototype
        this.equipments = new_device;
        return true;
    }

    public String getCompName(){
        return this.CompName;
    }

    public int getId(){
        return this.id;
    }

    public boolean getRelevance(){
        return this.relevance;
    }

    public String getLDate(){
        return this.lDate;
    }

    public String getFDate(){
        return this.fDate;
    }

    public int getPrice(){
        return this.price;
    }

    public List <DevicesSqlDao> GetEquipments(){
        return this.equipments;
    }
}

