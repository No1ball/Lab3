package com.example.springtest.controllers;


import com.example.springtest.entity.DevicesSqlDao;
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
    private DevicesRepo devicesRepo;

    @GetMapping("/equpments")
    public String showEquipments(Model model) {
        Iterable <DevicesSqlDao> devices = devicesRepo.findAll();
        model.addAttribute("devices", devices);
        return "equpments";
    }

    @RequestMapping("/")
    public String index() {
        return "start";
    }

    @RequestMapping("/start")
    public String test() {
        return "start";
    }


}
