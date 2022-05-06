package com.example.springtest.entity;

import javax.persistence.*;

import java.util.List;



@Entity
@Table(name = "client")
public class ClientsSqlDao extends CompanySqlDAO {

    @Column(name = "totalSumm")
    private int totalSumm = 0;
    @Column(name="oldContractId")
    @OneToMany (mappedBy="id", fetch=FetchType.LAZY)
    private List<ContractsSqlDao> oldContractID;



    public boolean setTotalSumm(int new_summ){ //prototype
        this.totalSumm = new_summ;
        return true;
    }

    public boolean setOldContractID(List<ContractsSqlDao> new_oldcntrid){ //prototype
        this.oldContractID = new_oldcntrid;
        return true;
    }



    public int getTotalSumm(){
        return this.totalSumm;
    }

    public List <ContractsSqlDao> getOldCntrctID(){
        return this.oldContractID;
    }


}
