package com.example.springtest.service.deviceService.impl;

import com.example.springtest.entity.DevicesSqlDao;

import java.util.List;

public interface DeviceService<T> {
    void getPDF(int value, String name);
    List<DevicesSqlDao> search(String name);
    T addDevice(DevicesSqlDao devices);
    void delDev(int id);
    DevicesSqlDao putDec(int id, DevicesSqlDao devices);
    List<DevicesSqlDao> getDevices();

    List<DevicesSqlDao> sortByPrice(String name);
    List<DevicesSqlDao> sortByCount(String name);
}
