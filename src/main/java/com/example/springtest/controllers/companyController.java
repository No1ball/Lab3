package com.example.springtest.controllers;

import com.example.springtest.entity.ClientsSqlDao;
import com.example.springtest.entity.CompanySqlDAO;
import com.example.springtest.entity.DevicesSqlDao;
import com.example.springtest.repos.ClientsRepo;

import com.example.springtest.repos.CompanyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class companyController {

    @Autowired
    private ClientsRepo companyRepo;

    @GetMapping("/firstContacts")
    public String showFirstContracts(Model model){
        Iterable <ClientsSqlDao> clients = companyRepo.findAll();
        model.addAttribute("clients", clients);
        return "firstContacts";
    }

    @PostMapping("/add_company")
    public String addCompany(@RequestBody ClientsSqlDao company){
        companyRepo.save(company);
        return "firstContacts";
    }
}
