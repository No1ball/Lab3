package com.example.springtest.service.contractsService;

import com.example.springtest.entity.ContractsSqlDao;
import com.example.springtest.entity.DevicesSqlDao;

import java.util.Date;
import java.util.List;

public interface ContractsService<T> {

    void getPDF(List<T> list);
    T searchCompName(String name);
    T addDevice(ContractsSqlDao devices);
    void financePdf(List<T> list);
    void delDev(int id);
    T noContractId(int idc, int idd);
    T putDec(int id, ContractsSqlDao devices);
    T viewId(int id);
    T getDevices();
    T searchContracts(String fDate, String lDate,String name);
    List<ContractsSqlDao> getRelev(String name);
    List<ContractsSqlDao> getNotRelev(String name);
}
