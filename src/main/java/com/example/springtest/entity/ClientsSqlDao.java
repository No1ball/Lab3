package com.example.springtest.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

import java.util.List;



@Entity
@Table(name = "client")
public class ClientsSqlDao extends CompanySqlDAO {

    @Column(name = "totalSumm")
    private int totalSumm = -1;
    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "contractNumber")
    @JsonManagedReference
    private ContractsSqlDao contractID;
    private int num;
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