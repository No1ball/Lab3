package com.example.springtest.controllers;


import com.example.springtest.entity.ClientsSqlDao;
import com.example.springtest.repos.ClientsRepo;
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
    private ClientsRepo companyRepo;

    @RequestMapping("/")
    public String index() {
        return "start";
    }

    @RequestMapping("/start")
    public String test() {
        return "start";
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
