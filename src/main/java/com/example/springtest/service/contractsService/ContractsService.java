package com.example.springtest.service.contractsService;

import com.example.springtest.entity.ContractsSqlDao;
import com.example.springtest.entity.DevicesSqlDao;

import java.util.Date;
import java.util.List;

public interface ContractsService<T> {

    void getPDF(int value, String name);

    T searchCompName(String name);

    T addDevice(ContractsSqlDao devices);
    void financePdf();
    void delDev(int id);
    T noContractId(int idc, int idd);
    T putDec(int id, ContractsSqlDao devices);
    T viewId(int id);
    T getDevices();
    T searchContracts(String fDate, String lDate);
    List<ContractsSqlDao> getRelev();
    List<ContractsSqlDao> getNotRelev();
}
