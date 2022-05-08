package com.example.springtest.controllers;

import com.example.springtest.entity.DevicesSqlDao;
import com.example.springtest.repos.ClientsRepo;
import com.example.springtest.repos.ContractsRepo;
import com.example.springtest.repos.DevicesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class deviceController {
    @Autowired
    private DevicesRepo devicesRepo;

    @GetMapping("/newequpments")
    public ResponseEntity test(){
        return ResponseEntity.ok(devicesRepo.findAll());
    }

    @PostMapping("/add_device")
    public ResponseEntity addDevice(@RequestBody DevicesSqlDao devices){
        return ResponseEntity.ok(devicesRepo.save(devices));
    }

    @DeleteMapping("/equpments/del/{id}")
    public String delDev(@PathVariable("id") int id){
        devicesRepo.deleteById(id);
        return "equpments";
    }

    @PutMapping("/equpments/ed/{id}")
    public ResponseEntity putDec(@PathVariable("id") int id, @RequestBody DevicesSqlDao devices){
        DevicesSqlDao device = devicesRepo.findById(id).orElseThrow();
        device.setId(devices.getId());
        device.setName(devices.getName());
        device.setPrice(devices.getPrice());
        device.setCountSale(devices.getCountSale());
        device.setTotalSumm(devices.getTotalSumm());
        return ResponseEntity.ok(devicesRepo.save(device));
    }
}
