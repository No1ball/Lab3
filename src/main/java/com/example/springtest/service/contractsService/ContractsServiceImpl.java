package com.example.springtest.service.contractsService;

import com.example.springtest.entity.ClientsSqlDao;
import com.example.springtest.entity.ContractsSqlDao;
import com.example.springtest.repos.ClientsRepo;
import com.example.springtest.repos.ContractsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContractsServiceImpl  implements  ContractsService{
    @Autowired
    private ContractsRepo contractsRepo;
    @Autowired
    private ClientsRepo clientsRepo;
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
    public ContractsSqlDao viewId(int id){
        return contractsRepo.findById(id).orElseThrow();
    }
    @Override
    public void delDev(int id){
        ContractsSqlDao contracts = contractsRepo.findById(id).orElseThrow();
        if(contracts.getClient()!= null){
            ClientsSqlDao client = contracts.getClient();
            client.setNum(0);
            client.setTotalSumm(client.getTotalSumm()-contracts.getPrice());
            client.setContractId(null);
            contracts.setClient(null);
            clientsRepo.saveAll(Arrays.asList(client));
        }else if(contracts.getOldClient()!=null){
            ClientsSqlDao client = contracts.getOldClient();
            List<ContractsSqlDao> clientsCntr = client.getOldContracts();
            clientsCntr.remove(contracts);
            //client.setTotalSumm(client.getTotalSumm()-contracts.getPrice());
            contracts.setOldClient(null);
            clientsRepo.saveAll(Arrays.asList(client));
        }
        contractsRepo.deleteById(id);
    }
    @Override
    public ContractsSqlDao putDec(int id, ContractsSqlDao devices){
        ContractsSqlDao contract = contractsRepo.findById(id).orElseThrow();
        contract.setCompName(devices.getCompName());
        contract.setLDate(devices.getDateLDate());
        contract.setFDate(devices.getDateFDate());
        contract.setPrice();
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