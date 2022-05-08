package com.example.springtest.controllers;

import com.example.springtest.entity.ContractsSqlDao;
import com.example.springtest.repos.ContractsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class contractsController {
    @Autowired
    private ContractsRepo contractsRepo;



    @PostMapping("/add_contracts")
    public String addContracts(@RequestBody ContractsSqlDao contracts){
        contractsRepo.save(contracts);
        return "contracts";
    }
}
