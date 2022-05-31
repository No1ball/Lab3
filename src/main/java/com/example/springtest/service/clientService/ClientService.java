package com.example.springtest.service.clientService;

import com.example.springtest.entity.ClientsSqlDao;

import java.util.List;

public interface ClientService<T> {

    List<ClientsSqlDao> getCompany();
    void delCompany(int id);
    ClientsSqlDao putCompany(int id, ClientsSqlDao company);
    T addCompany(ClientsSqlDao company);

    List<ClientsSqlDao> topClient();
    List <ClientsSqlDao> searchCompany(String name);
    void toPdf(String name);
}
