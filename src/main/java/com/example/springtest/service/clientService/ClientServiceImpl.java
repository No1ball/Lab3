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
import java.util.*;
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
        ClientsSqlDao client = clientsRepo.findById(id).orElseThrow();
        ContractsSqlDao clientContr = client.getContractId();
        if(clientContr != null) {
            List<DevicesSqlDao> devises = clientContr.getEquipments();
            for (DevicesSqlDao eqiup : devises) {
                eqiup.delOneCntr(clientContr);
                eqiup.setNwStr();
            }
            clientContr.setEquipments(null);
        }
        List<ContractsSqlDao> oldCntr = client.getOldContracts();
        if(oldCntr!=null){
            for (ContractsSqlDao iContr: oldCntr) {
                for (DevicesSqlDao eqiup: iContr.getEquipments()) {
                    eqiup.delOneCntr(iContr);
                    eqiup.setNwStr();
                }
                iContr.setEquipments(null);
            }
        }
        clientsRepo.deleteById(id);
    }
    @Override
    public ClientsSqlDao putCompany(int id, ClientsSqlDao company){
        ClientsSqlDao comp = clientsRepo.findById(id).orElseThrow();
        ContractsSqlDao contract;
        comp.setName(company.getName());
        comp.setContact(company.getContact());
        if(company.getNum()!= 0){
            if(comp.getNum() != 0){
                contract = contractsRepo.findById(comp.getNum()).orElseThrow();
                contract.setClient(null);
            }
            contract = contractsRepo.findById(company.getNum()).orElseThrow();
            contract.setClient(comp);
            comp.setContractId(contract);
            comp.setNum(contract.getId());
            contract.setCompName(comp.getName());
            comp.setTotalSumm(comp.getTotalSumm()+contract.getPrice());
        }
        if(comp.getOldContracts()!=null){
            List<ContractsSqlDao> oldContr = comp.getOldContracts();
            String neName = company.getName();
            oldContr.forEach(e->e.setNewCompName(neName));
        }
        return clientsRepo.save(comp);
    }
    @Override
    public Iterable<ClientsSqlDao> toOld(int id){
        ClientsSqlDao company = clientsRepo.findById(id).orElseThrow();
        company.addOldContract(company.getContractId());
        ContractsSqlDao cont = contractsRepo.findById(company.getContractId().getId()).orElseThrow();
        cont.setClient(null);
        cont.setOldClient(company);
        company.setContractId(null);
        company.setNum(0);
        List<ClientsSqlDao> clie = Arrays.asList(company);
        return clientsRepo.saveAll(clie);
    }

    @Override
    public Iterable<ClientsSqlDao> noContractId(int id){
        ClientsSqlDao clie = clientsRepo.findById(id).orElseThrow();
        ContractsSqlDao contr = clie.getContractId();
        clie.setTotalSumm(clie.getTotalSumm()-contr.getPrice());
        contr.setClient(null);
        clie.setContractId(null);
        clie.setNum(0);
        return clientsRepo.saveAll(Arrays.asList(clie));
    }


    @Override
    public Iterable<ClientsSqlDao> addCompany(ClientsSqlDao company){
        ContractsSqlDao contract = new ContractsSqlDao();
        if(company.getNum() != 0){
            try{
                contract = contractsRepo.findById(company.getNum()).orElseThrow();
                contract.setClient(company);
                contract.setCompName(company.getName());
            }catch (Exception ex) {
                contract.setId(company.getNum());
                contract.setCompName(company.getName());
                contract.setLDate(new Date());
                contract.setFDate(new Date());
                contract.setPrice();
                contractsRepo.save(contract);
            }
            company.setContractId(contract);
            contract.setClient(company);
            company.setTotalSumm(contract.getPrice());
        }else{
            company.setTotalSumm(0);
        }
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