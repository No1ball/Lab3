package com.example.springtest.controllers;
import com.example.springtest.entity.ClientsSqlDao;
import com.example.springtest.repos.ClientsRepo;
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

    @PostMapping ("/add_clients")
    public String saveClient(@RequestBody ClientsSqlDao client){
         clientsRepo.save(client);
         return "clients";
    }


}
