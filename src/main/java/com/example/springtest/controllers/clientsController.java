package com.example.springtest.controllers;
import com.example.springtest.entity.ClientsSqlDao;
import com.example.springtest.repos.ClientsRepo;
import com.example.springtest.service.clientService.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class clientsController {
    @Autowired
    private ClientService clientsService;

    @PostMapping ("/add_clients")
    public ResponseEntity saveClient(@RequestBody ClientsSqlDao client){
        return ResponseEntity.ok(clientsService.addCompany(client));
    }
    @DeleteMapping("/client/del/onlyContractId/{id}")
    public ResponseEntity on1(@PathVariable("id") int id){
        return ResponseEntity.ok(clientsService.noContractId(id));
    }
    @PutMapping("/client/toOld/{id}")
    public ResponseEntity toOld(@PathVariable("id") int id){
        return ResponseEntity.ok(clientsService.toOld(id));
    }
    @GetMapping("/client")
    public ResponseEntity getClient(){
        return ResponseEntity.ok(clientsService.getCompany());
    }
    @GetMapping("/clients")
    public String viewClient(){
        return "clients";
    }
    @GetMapping("/client/topSumm")
    public ResponseEntity topClient(){
        return ResponseEntity.ok(clientsService.topClient());
    }
    @DeleteMapping("/client/del/{id}")
    public String deleteClient(@PathVariable("id") int id){
        clientsService.delCompany(id);
        return "clients";
    }
    @GetMapping("/client1")
    public ResponseEntity search(@RequestParam("name") String name){
        return ResponseEntity.ok(clientsService.searchCompany(name));
    }
    @PutMapping("/client/ed/{id}")
    public ResponseEntity editClient(@PathVariable("id") int id, @RequestBody ClientsSqlDao client){
        return ResponseEntity.ok(clientsService.putCompany(id, client));
    }
}