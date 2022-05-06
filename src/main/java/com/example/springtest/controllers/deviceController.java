package com.example.springtest.controllers;

import com.example.springtest.entity.DevicesSqlDao;
import com.example.springtest.repos.DevicesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class deviceController {
    @Autowired
    private DevicesRepo devicesRepo;

    @GetMapping("/equpments")
    public List<DevicesSqlDao> showEquipments(Model model){
        Iterable <DevicesSqlDao> devices = devicesRepo.findAll();
        model.addAttribute("devices", devices);
        List <DevicesSqlDao> deviceList= new ArrayList<>();
        devicesRepo.findAll().forEach(deviceList::add);
        return deviceList;
    }

    @PostMapping("/add_device")
    public void addDevice(@RequestBody DevicesSqlDao devices){
        devicesRepo.save(devices);

    }
}
