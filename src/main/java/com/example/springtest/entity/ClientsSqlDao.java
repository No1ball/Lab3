package com.example.springtest.entity;

import javax.persistence.*;

import java.util.List;



@Entity
@Table(name = "client")
public class ClientsSqlDao extends CompanySqlDAO {

    @Column(name = "totalSumm")
    private int TotalSumm = 0;
    @OneToMany (mappedBy="id", fetch=FetchType.LAZY)
    private List<ContractsSqlDao> OldContractID;



    public boolean setTotalSumm(int new_summ){ //prototype
        this.TotalSumm = new_summ;
        return true;
    }

    public boolean setOldContractID(List<ContractsSqlDao> new_oldcntrid){ //prototype
        this.OldContractID = new_oldcntrid;
        return true;
    }



    public int getTotalSumm(){
        return this.TotalSumm;
    }

    public List <ContractsSqlDao> getOldCntrctID(){
        return this.OldContractID;
    }
}
