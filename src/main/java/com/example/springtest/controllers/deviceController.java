package com.example.springtest.controllers;

import com.example.springtest.entity.DevicesSqlDao;
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

    @GetMapping("/equpments2")
    public ResponseEntity test(){
        return ResponseEntity.ok(devicesRepo.findAll());
    }
    @PostMapping("/add_device")
    public ResponseEntity addDevice(@RequestBody DevicesSqlDao devices){
        return ResponseEntity.ok(devicesRepo.save(devices));

    }
}
