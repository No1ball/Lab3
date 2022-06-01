package com.example.springtest.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

import java.util.List;



@Entity
@Table(name = "client")
public class ClientsSqlDao extends CompanySqlDAO {

    @Column(name = "totalSumm")
    private int totalSumm = -1;
    @OneToOne(cascade = {CascadeType.REFRESH, CascadeType.REMOVE,CascadeType.DETACH})
    @JoinColumn(name = "contractNumber")
    @JsonManagedReference(value="client")
    private ContractsSqlDao contractID;
    @OneToMany(mappedBy = "oldClient", cascade = {CascadeType.REFRESH, CascadeType.REMOVE,CascadeType.DETACH})
    private List<ContractsSqlDao> oldContracts;
    private int num;
    private String tempStr;
    public boolean setTempStr(String new_CompName){ //prototype
        this.tempStr= new_CompName;
        return true;
    }
    public String getTempStr(){
        return this.tempStr;
    }
    public boolean setOldContracts(List<ContractsSqlDao> nOld){
        this.oldContracts = nOld;
        return true;
    }
    public List<ContractsSqlDao> getOldContracts(){
        return this.oldContracts;
    }
    public boolean setNum(int new_summ){ //prototype
        this.num = new_summ;
        return true;
    }
    public int getNum(){
        return this.num;
    }
    public boolean setContractId(ContractsSqlDao new_summ){ //prototype
        this.contractID = new_summ;
        return true;
    }

    public boolean setTotalSumm(int new_summ){ //prototype
        this.totalSumm = new_summ;
        return true;
    }



    public int getTotalSumm(){
        return this.totalSumm;
    }
    public ContractsSqlDao getContractId(){
        return this.contractID;
    }


}