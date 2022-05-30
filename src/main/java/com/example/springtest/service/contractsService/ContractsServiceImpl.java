package com.example.springtest.service.contractsService;

import com.example.springtest.entity.ContractsSqlDao;
import com.example.springtest.repos.ContractsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContractsServiceImpl  implements  ContractsService{
    @Autowired
    private ContractsRepo contractsRepo;

    public static final String FONT = "./src/main/resources/arialmt.ttf";
    @Override
    public List<ContractsSqlDao> searchCompName(String name){
        return contractsRepo.findByCompNameContainingIgnoreCaseOrderByCompName(name);
    }
    @Override
    public ContractsSqlDao addDevice(ContractsSqlDao devices){
        devices.setRelevance();
        return contractsRepo.save(devices);
    }
    @Override
    public void delDev(int id){
        contractsRepo.deleteById(id);
    }
    @Override
    public ContractsSqlDao putDec(int id, ContractsSqlDao devices){
        ContractsSqlDao contract = contractsRepo.findById(id).orElseThrow();
        contract.setCompName(devices.getCompName());
        contract.setPrice(devices.getPrice());
        contract.setLDate(devices.getDateLDate());
        contract.setFDate(devices.getDateFDate());
        contract.setPrice(devices.getPrice());
        contract.setRelevance();
        return contractsRepo.save(contract);
    }
    @Override
    public List<ContractsSqlDao> getDevices(){
        List <ContractsSqlDao> contractsList = contractsRepo.findAll();
        return contractsList;
    }
    @Override
    public void getPDF(int value, String name)
    {

    }

    @Override
    public List<ContractsSqlDao> getRelev(){
        List <ContractsSqlDao> relList, contractsList;
        contractsList = contractsRepo.findAll();
        relList = contractsList.stream().filter(element-> ((element.getRelevance() == true))).collect(Collectors.toList());
        return relList;
    }

    @Override
    public List<ContractsSqlDao> getNotRelev(){
        List <ContractsSqlDao> relList, contractsList;
        contractsList = contractsRepo.findAll();
        relList = contractsList.stream().filter(element-> ((element.getRelevance() == false))).collect(Collectors.toList());
        return relList;
    }

}
