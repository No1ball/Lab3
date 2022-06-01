package com.example.springtest.entity;



import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.apache.logging.log4j.message.StringFormattedMessage;

import javax.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="contracts")
public class ContractsSqlDao {
    @Id
    @Column(name = "Id")
    private int id;
    @Column(name="name")
    private String compName;
    @Column(name = "relevance")
    private boolean relevance;
    @Column(name = "f_date")
    private Date fdate;
    @Column(name = "l_date")
    private Date ldate;
    @Column(name = "price")
    private int price;

    @ManyToMany(targetEntity = DevicesSqlDao.class, fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.REMOVE, CascadeType.DETACH})
    @JoinTable (name="equipments",
            joinColumns=@JoinColumn (name="Id"),
            inverseJoinColumns={@JoinColumn(name="count")})
    private List<DevicesSqlDao> equipments;
    @OneToOne(mappedBy = "contractID", cascade = {CascadeType.REFRESH, CascadeType.REMOVE, CascadeType.DETACH})
    @JsonBackReference(value="client")
    private ClientsSqlDao client;




    public boolean setCompName(String new_CompName){ //prototype
        this.compName = new_CompName;
        return true;
    }
    public boolean setId(int new_CompName){ //prototype
        this.id = new_CompName;
        return true;
    }
    public boolean setRelevance(){ //prototype
        this.relevance = this.getDateLDate().after(new Date());
        return true;
    }

    public boolean setFDate(Date new_fDate){ //prototype
        this.fdate = new_fDate;
        return true;
    }

    public boolean setLDate(Date new_lDate){ //prototype
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
    public boolean setClient(ClientsSqlDao newclient ){
        this.client = newclient;
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
    public Date getDateLDate(){
        return this.ldate;
    }
    public String getLDate(){
        return this.ldate.toString().substring(0,10);
    }
    public Date getDateFDate(){
        return this.fdate;
    }
    public String getFDate(){
        return  this.fdate.toString().substring(0,10);
    }

    public int getPrice(){
        return this.price;
    }

    public List <DevicesSqlDao> getEquipments(){
        return this.equipments;
    }

    public ClientsSqlDao getClient(){
        return this.client;
    }
    public boolean setOneEquip(DevicesSqlDao one){
        this.equipments.add(one);
        return true;
    }
}