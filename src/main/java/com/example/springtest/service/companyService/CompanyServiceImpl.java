package com.example.springtest.service.companyService;
import com.example.springtest.entity.ClientsSqlDao;
import com.example.springtest.repos.ClientsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService{
    @Autowired
    private ClientsRepo companyRepo;

    @Override
    public List<ClientsSqlDao> getCompany(){
        List<ClientsSqlDao> fullList = companyRepo.findAll();
        List<ClientsSqlDao> listComp = fullList.stream().filter(element->(element.getContractId()==0)).collect(Collectors.toList());
        return listComp;
    }
    @Override
    public void delCompany(int id){
        companyRepo.deleteById(id);
    }
    @Override
    public ClientsSqlDao putCompany(int id, ClientsSqlDao company){
        ClientsSqlDao comp = companyRepo.findById(id).orElseThrow();
        comp.setName(company.getName());
        comp.setContact(company.getContact());
        return companyRepo.save(comp);
    }
    @Override
    public ClientsSqlDao addCompany(ClientsSqlDao company){
        return companyRepo.save(company);
    }
    @Override
    public ClientsSqlDao toClient(int id, ClientsSqlDao client){
        ClientsSqlDao clien = companyRepo.findById(id).orElseThrow();
        clien.setContractId(client.getContractId());
        clien.setTotalSumm(client.getTotalSumm());
        return companyRepo.save(clien);
    }
    @Override
    public List<ClientsSqlDao> searchCompany(String name){
        return companyRepo.findByNameContainsIgnoreCaseOrderByName(name);
    }
    @Override
    public void toPdf(String name){

    }
}
