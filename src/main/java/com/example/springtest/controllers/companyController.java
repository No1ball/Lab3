package com.example.springtest.controllers;

import com.example.springtest.entity.ClientsSqlDao;
import com.example.springtest.repos.ClientsRepo;
import com.example.springtest.service.companyService.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class companyController {

    @Autowired
    private CompanyService companyService;
    @GetMapping("/firstContacts")
    public String showFirstContracts(){
        return "firstContacts";
    }
    @GetMapping("/company")
    public ResponseEntity shownCompany(){
        return ResponseEntity.ok(companyService.getCompany());
    }
    @PostMapping("/add_company")
    public ResponseEntity addCompany(@RequestBody ClientsSqlDao company){
        return ResponseEntity.ok(companyService.addCompany(company));
    }
    @GetMapping("/company1")
    public ResponseEntity search(@RequestParam("name") String name){
        return ResponseEntity.ok(companyService.searchCompany(name));
    }
    @PutMapping("/company/ed/{id}")
    public ResponseEntity editComp(@PathVariable("id") int id, @RequestBody ClientsSqlDao client){
        return ResponseEntity.ok(companyService.putCompany(id, client));
    }
    @DeleteMapping("/company/del/{id}")
    public String delCompany(@PathVariable("id") int id){
        companyService.delCompany(id);
        return "firstContacts";
    }
    @PutMapping("/company/toClient/{id}")
    public ResponseEntity toClient(@PathVariable("id") int id, @RequestBody ClientsSqlDao client){
        return ResponseEntity.ok(companyService.toClient(id, client));
    }
}
