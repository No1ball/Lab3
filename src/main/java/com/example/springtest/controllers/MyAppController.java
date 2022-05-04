package com.example.springtest.controllers;

import com.example.springtest.DAO.ContractsDAO;
import com.example.springtest.entity.DevicesSqlDao;
import com.example.springtest.models.Contracts;
import com.example.springtest.models.Devices;
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
    private final ContractsDAO contractsDAO;

    public MyAppController(ContractsDAO contractsDAO){
        this.contractsDAO = contractsDAO;
    }
    @Autowired
    private DevicesRepo devicesRepo;

    @RequestMapping("/")
    public String index() {
        return "start";
    }

    @RequestMapping("/start")
    public String test() {
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
    public String showEquipments(Model model){
        Iterable <DevicesSqlDao> devices = devicesRepo.findAll();
        model.addAttribute("devices", devices);
        return "equpments";
    }

    @GetMapping("/firstContracts")
    public String showFirstContracts(){
        return "firstContracts";
    }

    @PostMapping("/add_device")
    public void addDevice(@RequestBody DevicesSqlDao devices){
        devicesRepo.save(devices);
    }

}
