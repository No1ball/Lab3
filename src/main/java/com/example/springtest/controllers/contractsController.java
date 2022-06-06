package com.example.springtest.controllers;

import com.example.springtest.entity.ClientsSqlDao;
import com.example.springtest.entity.ContractsSqlDao;
import com.example.springtest.service.contractsService.ContractsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class contractsController {
    @Autowired
    private ContractsServiceImpl contractsService;

    @GetMapping("/contracts")
    public String showContracts() {
        contractsService.getDevices();
        return "contracts";
    }
    @PostMapping("/add_contracts")
    public ResponseEntity addContracts(@RequestBody ContractsSqlDao contracts){
        return ResponseEntity.ok(contractsService.addDevice(contracts));
    }
    @GetMapping("/finance/topdf/{list}")
    public String getPDF1(@PathVariable("list") String list){
        System.out.println(list);
        String[] array = list.split(",");
        List<Integer> intsList = new ArrayList<Integer>(array.length);
        for (int i = 0; i < array.length; i++) {
            intsList.add(i, Integer.parseInt(array[i]));
        }
        contractsService.financePdf(intsList);
        return "finance";
    }
    @GetMapping("/newcontracts")
    public ResponseEntity view(){
        return ResponseEntity.ok(contractsService.getDevices());
    }
    @PutMapping("/contracts/ed/{id}")
    public ResponseEntity putDec(@PathVariable("id") int id, @RequestBody ContractsSqlDao devices){
        return ResponseEntity.ok(contractsService.putDec(id, devices));
    }
    @DeleteMapping("/contracts/del/{id}")
    public String deleteContract(@PathVariable("id") int id){
        contractsService.delDev(id);
        return "contracts";
    }
    @PutMapping("/contracts/edir/{id}")
    public ResponseEntity tem(@PathVariable("id") int id, @RequestBody ContractsSqlDao dd){
        System.out.println(id);
        System.out.println(dd.getTempStr());
        return ResponseEntity.ok(contractsService.noContractId(id, Integer.parseInt(dd.getTempStr())));
    }
    @GetMapping("/finance")
    public String finance(){
        return "finance";
    }
    @GetMapping("/contracts1")
    public ResponseEntity search(@RequestParam("name") String name){
        return  ResponseEntity.ok(contractsService.searchCompName(name));
    }
    @GetMapping("/contracts/topdf/{list}")
    public String pdfCon(@PathVariable("list") String list){
        System.out.println(list);
        String[] array = list.split(",");
        List<Integer> intsList = new ArrayList<Integer>(array.length);
        for (int i = 0; i < array.length; i++) {
            intsList.add(i, Integer.parseInt(array[i]));
        }
        contractsService.financePdf(intsList);
        return "contracts";
    }
    @GetMapping("/contracts/view/{id}")
    public String find(@PathVariable("id") int id){
        if(id == 0){
            return "clients";
        }else{
        return "contracts/{id}";}
    }
    @GetMapping("/contracts/searchContracts")
    public ResponseEntity searchContracts(@RequestParam("name1") String fDate, @RequestParam("name2") String lDate, @RequestParam("name") String name){
        return ResponseEntity.ok(contractsService.searchContracts(fDate, lDate, name));
    }
    @GetMapping("/contracts/ht/{id}")
    public ResponseEntity findId(@PathVariable("id") int id){
        return ResponseEntity.ok(contractsService.viewId(id));
    }
    @GetMapping("/contracts/relev")
    public ResponseEntity getRelev(@RequestParam("name") String name){
        return ResponseEntity.ok(contractsService.getRelev(name));
    }
    @GetMapping("/contracts/notRelev")
    public ResponseEntity getNotRelev(@RequestParam("name") String name){
        return ResponseEntity.ok(contractsService.getNotRelev(name));
    }
}
