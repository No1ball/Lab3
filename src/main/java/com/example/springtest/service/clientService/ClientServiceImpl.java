package com.example.springtest.service.clientService;

import com.example.springtest.entity.ClientsSqlDao;
import com.example.springtest.entity.DevicesSqlDao;
import com.example.springtest.repos.ClientsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.swing.*;
import java.io.FileOutputStream;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService{
    @Autowired
    private ClientsRepo clientsRepo;
    @Override
    public List<ClientsSqlDao> getCompany(){
        List<ClientsSqlDao> fullList = clientsRepo.findAll();
        List<ClientsSqlDao> listComp = fullList.stream().filter(element->(element.getContractId()>0)).collect(Collectors.toList());
        return listComp;
    }
    @Override
    public void delCompany(int id){
        clientsRepo.deleteById(id);
    }
    @Override
    public ClientsSqlDao putCompany(int id, ClientsSqlDao company){
        ClientsSqlDao comp = clientsRepo.findById(id).orElseThrow();
        comp.setName(company.getName());
        comp.setContact(company.getContact());
        comp.setContractId(company.getContractId());
        comp.setTotalSumm(company.getTotalSumm());
        return clientsRepo.save(comp);
    }
    @Override
    public ClientsSqlDao addCompany(ClientsSqlDao company){
        return clientsRepo.save(company);
    }
    @Override
    public List<ClientsSqlDao> topClient(){
        List<ClientsSqlDao> client = clientsRepo.findAll();
        List<ClientsSqlDao> listComp = client.stream().filter(element->(element.getContractId()>0)).collect(Collectors.toList());
        return listComp.stream().sorted(Comparator.comparingInt(ClientsSqlDao::getTotalSumm).reversed()).collect(Collectors.toList());
    }
    @Override
    public List<ClientsSqlDao> searchCompany(String name){
        List<ClientsSqlDao> client = clientsRepo.findByNameContainsIgnoreCaseOrderByName(name);
        return client.stream().filter(element->(element.getContractId()>0)).collect(Collectors.toList());

    }
    @Override
    public void toPdf(String name){

    }
}
