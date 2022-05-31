package com.example.springtest.service.clientService;

import com.example.springtest.entity.ClientsSqlDao;

import java.util.List;

public interface ClientService {

    List<ClientsSqlDao> getCompany();
    void delCompany(int id);
    ClientsSqlDao putCompany(int id, ClientsSqlDao company);
    ClientsSqlDao addCompany(ClientsSqlDao company);

    List<ClientsSqlDao> topClient();
    List <ClientsSqlDao> searchCompany(String name);
    void toPdf(String name);
}
