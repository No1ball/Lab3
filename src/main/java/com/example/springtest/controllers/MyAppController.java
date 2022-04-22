package com.example.springtest.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyAppController {
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
    public String showContracts(){return "contracts";}

    @GetMapping("/equpments")
    public String showEquipments(){return "equpments";}

    @GetMapping("/firstContracts")
    public String showFirstContracts(){return "firstContracts";}
}
