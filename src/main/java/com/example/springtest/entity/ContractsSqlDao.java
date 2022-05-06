package com.example.springtest.entity;



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
    private String compName;
    @Column(name = "relevance")
    private boolean relevance;
    @Column(name = "f_date")
    private String fdate;
    @Column(name = "l_date")
    private String ldate;
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
        this.compName = new_CompName;
        return true;
    }

    public boolean setRelevance(boolean new_relevance){ //prototype
        this.relevance = new_relevance;
        return true;
    }

    public boolean setFDate(String new_fDate){ //prototype
        this.fdate = new_fDate;
        return true;
    }

    public boolean setLDate(String new_lDate){ //prototype
        this.ldate = new_lDate;
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
        return this.compName;
    }

    public int getId(){
        return this.id;
    }

    public boolean getRelevance(){
        return this.relevance;
    }

    public String getLDate(){
        return this.ldate;
    }

    public String getFDate(){
        return this.fdate;
    }

    public int getPrice(){
        return this.price;
    }

    public List <DevicesSqlDao> getEquipments(){
        return this.equipments;
    }
}

