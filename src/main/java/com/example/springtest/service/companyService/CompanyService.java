package com.example.springtest.service.companyService;

import com.example.springtest.entity.ClientsSqlDao;

import java.util.List;

public interface CompanyService {
    List <ClientsSqlDao> getCompany();
    void delCompany(int id);
    ClientsSqlDao putCompany(int id, ClientsSqlDao company);
    ClientsSqlDao addCompany(ClientsSqlDao company);
    ClientsSqlDao toClient(int id, ClientsSqlDao client);
    List <ClientsSqlDao> searchCompany(String name);
    void toPdf(String name);
}
