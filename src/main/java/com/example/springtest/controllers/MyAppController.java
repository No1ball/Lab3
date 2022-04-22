package com.example.springtest.controllers;

import com.example.springtest.DAO.ContractsDAO;
import com.example.springtest.models.Contracts;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyAppController {
    private final ContractsDAO contractsDAO;

    public MyAppController(ContractsDAO contractsDAO){
        this.contractsDAO = contractsDAO;
    }

    /**
     * @return start page, google requestMapping
     * */
    @RequestMapping("/")
    public String index() {
        return "start";
    }

    @GetMapping("/clients")
    public String showClients(){return "clients";}

    @GetMapping("/contracts")
    public String showContracts(Model model) {
        model.addAttribute("contracts", contractsDAO.showContracts());
        return "contracts";
    }

    @GetMapping("/equpments")
    public String showEquipments(){return "equpments";}

    @GetMapping("/firstContracts")
    public String showFirstContracts(){return "firstContracts";}
}
