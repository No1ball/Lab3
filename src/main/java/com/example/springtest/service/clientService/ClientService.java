package com.example.springtest.service.clientService;

import com.example.springtest.entity.ClientsSqlDao;

import java.util.Date;
import java.util.List;

public interface ClientService<T> {

    T getCompany();
    void delCompany(int id);
    T putCompany(int id, ClientsSqlDao company);
    T addCompany(ClientsSqlDao company);
    T toOld(int id);
    T noContractId(int id);
    T topClient();

    T searchCompany(String name);
    void toPdf(String name);
}
