package com.example.springtest.service.contractsService;

import com.example.springtest.entity.ContractsSqlDao;

import java.util.List;

public interface ContractsService {

    void getPDF(int value, String name);

    List<ContractsSqlDao> searchCompName(String name);

    ContractsSqlDao addDevice(ContractsSqlDao devices);

    void delDev(int id);

    ContractsSqlDao putDec(int id, ContractsSqlDao devices);

    List<ContractsSqlDao> getDevices();

    List<ContractsSqlDao> getRelev();
    List<ContractsSqlDao> getNotRelev();
}
