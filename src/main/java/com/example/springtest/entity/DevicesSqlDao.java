package com.example.springtest.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "device")
public class DevicesSqlDao {
    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private int price;
    @Column(name = "countSale")
    private int countSale = 0;
    @Column(name = "totalSumm")
    private int totalSumm = 0;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iddevice")
    private int id;
    @ManyToMany(mappedBy = "equipments", cascade = {CascadeType.REFRESH, CascadeType.REMOVE,CascadeType.DETACH})
    @JsonBackReference(value="test")
    private List<ContractsSqlDao> contract;
    private String tempStr;
    public void setNwStr(){
        List<ContractsSqlDao> clList = this.contract;
        String nwStr = " ";
        List<Integer> intS = new ArrayList<>();
        for (ContractsSqlDao clien:clList) {
            intS.add(clien.getId());
        }
        for (Integer inT:intS) {
            nwStr = nwStr.concat(String.valueOf(inT));
        }
        this.tempStr = nwStr;
    }
    public List<ContractsSqlDao> getContract(){
        return this.contract;
    }
    public boolean setOneContract(ContractsSqlDao one){
        this.contract.add(one);
        return true;
    }
    public boolean setTempStr(String newname){
        this.tempStr = newname;
        return true;
    }
    public String getTempStr(){
        return this.tempStr;
    }
    public boolean setId(int new_id) {
        this.id = new_id;
        return true;
    }
    public boolean setContract(List<ContractsSqlDao> new_contract){
        this.contract = new_contract;
        return true;
    }

    public boolean setName(String new_name) { //prototype
        this.name = new_name;
        return true;
    }

    public boolean setPrice(int new_price){ //prototype
        this.price = new_price;
        return true;
    }

    public boolean setCountSale(int new_cntSale){ //prototype
        this.countSale = new_cntSale;
        return true;
    }

    public boolean setTotalSumm(){ //prototype
        this.totalSumm = this.getPrice()*this.getCountSale();
        return true;
    }

    public String getName(){
        return this.name;
    }

    public int getPrice(){
        return this.price;
    }

    public int getCountSale(){
        return this.countSale;
    }

    public int getTotalSumm(){
        return this.totalSumm;
    }

    public int getId() {
        return this.id;
    }
    public boolean delOneCntr(ContractsSqlDao one){
        this.contract.remove(one);
        return true;
    }
}


