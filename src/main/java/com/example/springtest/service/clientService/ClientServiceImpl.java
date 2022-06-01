package com.example.springtest.service.clientService;

import com.example.springtest.entity.ClientsSqlDao;
import com.example.springtest.entity.ContractsSqlDao;
import com.example.springtest.entity.DevicesSqlDao;
import com.example.springtest.repos.ClientsRepo;
import com.example.springtest.repos.ContractsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.swing.*;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService{
    @Autowired
    private ClientsRepo clientsRepo;
    @Autowired
    private ContractsRepo contractsRepo;
    @Override
    public List<ClientsSqlDao> getCompany(){
        List<ClientsSqlDao> fullList = clientsRepo.findAll();
        List<ClientsSqlDao> listComp = fullList.stream().filter(element->(element.getTotalSumm()>=0)).collect(Collectors.toList());
        return listComp;
    }
    @Override
    public void delCompany(int id){
        clientsRepo.deleteById(id);
    }
    @Override
    public ClientsSqlDao putCompany(int id, ClientsSqlDao company){
        ClientsSqlDao comp = clientsRepo.findById(id).orElseThrow();
        ContractsSqlDao contract;
        contract = contractsRepo.findById(company.getNum()).orElseThrow();
        comp.setName(company.getName());
        comp.setContact(company.getContact());
        company.setContractId(contract);
        comp.setTotalSumm(company.getTotalSumm());
        return clientsRepo.save(comp);
    }
    @Override
    public Iterable<ClientsSqlDao> addCompany(ClientsSqlDao company){
        ContractsSqlDao contract = new ContractsSqlDao();
        contract.setId(company.getNum());
        contract.setCompName(company.getName());
        contract.setLDate(new Date());
        contract.setFDate(new Date());
        contract.setPrice();
        contractsRepo.save(contract);
        company.setContractId(contract);
        contract.setClient(company);
        company.setTotalSumm(contract.getPrice());
        List<ClientsSqlDao> clie = Arrays.asList(company);
        return clientsRepo.saveAll(clie);
    }

    @Override
    public List<ClientsSqlDao> topClient(){
        List<ClientsSqlDao> client = clientsRepo.findAll();
        List<ClientsSqlDao> listComp = client.stream().filter(element->(element.getTotalSumm()>=0)).collect(Collectors.toList());
        return listComp.stream().sorted(Comparator.comparingInt(ClientsSqlDao::getTotalSumm).reversed()).collect(Collectors.toList());
    }
    @Override
    public List<ClientsSqlDao> searchCompany(String name){
        List<ClientsSqlDao> client = clientsRepo.findByNameContainsIgnoreCaseOrderByName(name);
        return client.stream().filter(element->(element.getTotalSumm()>=0)).collect(Collectors.toList());

    }
    @Override
    public void toPdf(String name){

    }
}