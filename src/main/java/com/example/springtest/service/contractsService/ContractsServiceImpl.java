package com.example.springtest.service.contractsService;

import com.example.springtest.entity.ClientsSqlDao;
import com.example.springtest.entity.ContractsSqlDao;
import com.example.springtest.entity.DevicesSqlDao;
import com.example.springtest.repos.ClientsRepo;
import com.example.springtest.repos.ContractsRepo;
import com.example.springtest.repos.DevicesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContractsServiceImpl  implements  ContractsService{
    @Autowired
    private ContractsRepo contractsRepo;
    @Autowired
    private ClientsRepo clientsRepo;
    @Autowired
    private DevicesRepo devicesRepo;
    public static final String FONT = "./src/main/resources/arialmt.ttf";
    @Override
    public List<ContractsSqlDao> searchCompName(String name){
        return contractsRepo.findByCompNameContainingIgnoreCaseOrderByCompName(name);
    }
    @Override
    public ContractsSqlDao addDevice(ContractsSqlDao devices){
        devices.setRelevance();
        if (devices.getTempStr()!=null) {
            String[] array = devices.getTempStr().split(",");
            List<Integer> intsList = new ArrayList<Integer>(array.length);
            for (int i = 0; i < array.length; i++) {
                intsList.add(i, Integer.parseInt(array[i]));
            }

            Iterable<DevicesSqlDao> cont = devicesRepo.findAllById(intsList);
            List<DevicesSqlDao> target = new ArrayList<>();
            cont.forEach(target::add);
            devices.setEquipments(target);
            for (int i = 0; i < target.size(); i++) {
                DevicesSqlDao tem = target.get(i);
                tem.setOneContract(devices);
            }
            devices.setPrice();
            List<ContractsSqlDao> dev = Arrays.asList(devices);
        }
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
            if(contracts.getEquipments()!=null){
                List<DevicesSqlDao> dev = contracts.getEquipments();
                for(int i = 0; i < dev.size(); i++){
                    DevicesSqlDao tem= dev.get(i);
                    tem.delOneCntr(contracts);
                    contracts.setPrice();
                }
                contracts.setEquipments(null);
            }
            clientsRepo.saveAll(Arrays.asList(client));

        }else if(contracts.getOldClient()!=null){
            ClientsSqlDao client = contracts.getOldClient();
            List<ContractsSqlDao> clientsCntr = client.getOldContracts();
            clientsCntr.remove(contracts);
            //client.setTotalSumm(client.getTotalSumm()-contracts.getPrice());
            contracts.setOldClient(null);
            clientsRepo.saveAll(Arrays.asList(client));
        }else if(contracts.getClient()== null && contracts.getOldClient()==null){
            if(contracts.getEquipments()!=null){
                List<DevicesSqlDao> dev = contracts.getEquipments();
                for(int i = 0; i < dev.size(); i++){
                    DevicesSqlDao tem= dev.get(i);
                    tem.delOneCntr(contracts);
                    contracts.setPrice();
                }
                contracts.setEquipments(null);
            }
        }
        contractsRepo.deleteById(id);
    }
    @Override
    public Iterable<ContractsSqlDao> putDec(int id, ContractsSqlDao devices){
        ContractsSqlDao contract = contractsRepo.findById(id).orElseThrow();
        if(contract.getOldClient()==null) {
            contract.setCompName(devices.getCompName());
            contract.setLDate(devices.getDateLDate());
            contract.setFDate(devices.getDateFDate());
            contract.setRelevance();
            int nowprice;
            if(contract.getClient()!= null){
                nowprice=contract.getClient().getTotalSumm()-contract.getPrice();
            }else{
                nowprice = 0;
            }
            if (!devices.getTempStr().equals(contract.getTempStr())) {
                String[] array = devices.getTempStr().split(",");
                List<Integer> intsList = new ArrayList<Integer>(array.length);
                for (int i = 0; i < array.length; i++) {
                    intsList.add(i, Integer.parseInt(array[i]));
                }
                Iterable<DevicesSqlDao> cont = devicesRepo.findAllById(intsList);
                List<DevicesSqlDao> target = new ArrayList<>();
                cont.forEach(target::add);
                contract.setPrice();
                contract.setEquipments(target);
                contract.setTempStr(devices.getTempStr());
                devicesRepo.saveAll(target);
            }
            if (contract.getClient() != null) {
                contract.getClient().setTotalSumm(nowprice+contract.getPrice());
            }
        }
        return contractsRepo.saveAll(Arrays.asList(contract));
    }
    @Override
    public Iterable<ContractsSqlDao> noContractId(int idc, int idd){
        ContractsSqlDao clie = contractsRepo.findById(idc).orElseThrow();
        DevicesSqlDao dev = devicesRepo.findById(idd).orElseThrow();
        dev.delOneCntr(clie);
        clie.delOneEquip(dev);
        clie.setTempStr(clie.getEquipments().toString());
        dev.setTempStr(dev.getContract().toString());
        devicesRepo.saveAll(Arrays.asList(dev));
        return contractsRepo.saveAll(Arrays.asList(clie));
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