package com.example.springtest.service.contractsService;

import com.example.springtest.entity.ContractsSqlDao;

import java.util.List;

public interface ContractsService<T> {

    void getPDF(int value, String name);

    T searchCompName(String name);

    T addDevice(ContractsSqlDao devices);

    void delDev(int id);

    T putDec(int id, ContractsSqlDao devices);

    T getDevices();

    List<ContractsSqlDao> getRelev();
    List<ContractsSqlDao> getNotRelev();
}
