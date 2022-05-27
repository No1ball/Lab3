package com.example.springtest.controllers;


import com.example.springtest.entity.ClientsSqlDao;
import com.example.springtest.entity.ContractsSqlDao;
import com.example.springtest.entity.DevicesSqlDao;
import com.example.springtest.repos.ClientsRepo;
import com.example.springtest.repos.ContractsRepo;
import com.example.springtest.repos.DevicesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MyAppController {
    @Autowired
    private ClientsRepo clientsRepo;

    @Autowired
    private ContractsRepo contractsRepo;

    @Autowired
    private ClientsRepo companyRepo;

    @RequestMapping("/")
    public String index() {
        return "start";
    }

    @RequestMapping("/start")
    public String test() {
        return "start";
    }



    @GetMapping("/contracts")
    public String showContracts(Model model) {
        Iterable <ContractsSqlDao> contracts = contractsRepo.findAll();
        model.addAttribute("contracts", contracts);
        return "contracts";
    }

    @GetMapping("/firstContacts")
    public String showFirstContracts(Model model){
        Iterable <ClientsSqlDao> clients = companyRepo.findAll();
        model.addAttribute("clients", clients);
        return "firstContacts";
    }

    @GetMapping("/clients")
    public List <ClientsSqlDao> showClients(Model model){
        Iterable <ClientsSqlDao> clients = clientsRepo.findAll();
        model.addAttribute("clients", clients);
        List <ClientsSqlDao> clientsList= new ArrayList<>();
        clientsRepo.findAll().forEach(clientsList::add);
        return clientsList;
    }
}
