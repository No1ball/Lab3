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
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class clientsController {
    @Autowired
    private ClientsRepo clientsRepo;

    @GetMapping("/clients")
    public List <ClientsSqlDao> showClients(Model model){
        Iterable <ClientsSqlDao> clients = clientsRepo.findAll();
        model.addAttribute("clients", clients);
        List <ClientsSqlDao> clientsList= new ArrayList<>();
        clientsRepo.findAll().forEach(clientsList::add);
        return clientsList;
    }


    @PostMapping ("/add_clients")
    public String saveClient(@RequestBody ClientsSqlDao client){
         clientsRepo.save(client);
         return "clients";
    }


}
