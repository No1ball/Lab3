package com.example.springtest.controllers;

import com.example.springtest.entity.ClientsSqlDao;
import com.example.springtest.repos.ClientsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class companyController {

    @Autowired
    private ClientsRepo companyRepo;

    @GetMapping("/company2")
    public ResponseEntity shownCompany(){
        return ResponseEntity.ok(companyRepo.findAll());
    }
    @PostMapping("/add_company")
    public String addCompany(@RequestBody ClientsSqlDao company){
        companyRepo.save(company);
        return "firstContacts";
    }
}
