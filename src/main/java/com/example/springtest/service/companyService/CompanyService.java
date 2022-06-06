package com.example.springtest.service.companyService;

import com.example.springtest.entity.ClientsSqlDao;

import java.util.List;

public interface CompanyService<T> {
    T getCompany();
    void delCompany(int id);
    T putCompany(int id, ClientsSqlDao company);
    T addCompany(ClientsSqlDao company);
    T toClient(int id, ClientsSqlDao client);
    T searchCompany(String name);
    void toPdf(List<T> name);
}
