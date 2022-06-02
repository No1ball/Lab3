package com.example.springtest.controllers;

import com.example.springtest.entity.ContractsSqlDao;
import com.example.springtest.service.contractsService.ContractsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/contracts1")
    public ResponseEntity search(@RequestParam("name") String name){
        return  ResponseEntity.ok(contractsService.searchCompName(name));
    }
    @GetMapping("/contracts/view/{id}")
    public String find(@PathVariable("id") int id){
        if(id == 0){
            return "clients";
        }else{
        return "contracts/{id}";}
    }
    @GetMapping("/contracts/ht/{id}")
    public ResponseEntity findId(@PathVariable("id") int id){
        return ResponseEntity.ok(contractsService.viewId(id));
    }
    @GetMapping("/contracts/relev")
    public ResponseEntity getRelev(){
        return ResponseEntity.ok(contractsService.getRelev());
    }
    @GetMapping("/contracts/notRelev")
    public ResponseEntity getNotRelev(){
        return ResponseEntity.ok(contractsService.getNotRelev());
    }
}
