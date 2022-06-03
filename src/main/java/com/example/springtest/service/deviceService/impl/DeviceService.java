package com.example.springtest.service.deviceService.impl;

import com.example.springtest.entity.ContractsSqlDao;
import com.example.springtest.entity.DevicesSqlDao;

import java.util.List;

public interface DeviceService<T> {
    void getPDF(int value, String name);
    T search(String name);
    T addDevice(DevicesSqlDao devices);
    void delDev(int id);
    T putDec(int id, DevicesSqlDao devices);
    T getDevices();
    T noContractId(int id);
    T viewId(int id);

    List<DevicesSqlDao> sortByPrice(String name);
    List<DevicesSqlDao> sortByCount(String name);
}
