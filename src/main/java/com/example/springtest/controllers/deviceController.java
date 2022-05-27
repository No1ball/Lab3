package com.example.springtest.controllers;

import com.example.springtest.entity.DevicesSqlDao;
import com.example.springtest.service.deviceService.impl.DeviceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class deviceController {
    @Autowired
    private DeviceServiceImpl deviceService;
    public static final String FONT = "./src/main/resources/arialmt.ttf";
    @GetMapping("/newequpments")
    public ResponseEntity view(){
        return ResponseEntity.ok(deviceService.getDevices());
    }

    @GetMapping("/equpments")
    public String showEquipments() {
        deviceService.getDevices();
        return "equpments";
    }
    @GetMapping("/equpments/topdf")
    public String getPDF()  {
        deviceService.getPDF();
        return "equpments";
    }
    @GetMapping("/equpments1")
    public ResponseEntity search(@RequestParam("name") String name){
        return  ResponseEntity.ok(deviceService.search(name));
    }
    @PostMapping("/add_device")
    public ResponseEntity addDevice(@RequestBody DevicesSqlDao devices){
        return ResponseEntity.ok(deviceService.addDevice(devices));
    }
    @DeleteMapping("/equpments/del/{id}")
    public String delDev(@PathVariable("id") int id){
        deviceService.delDev(id);
        return "equpments";
    }
    @PutMapping("/equpments/ed/{id}")
    public ResponseEntity putDec(@PathVariable("id") int id, @RequestBody DevicesSqlDao devices){
        return ResponseEntity.ok(deviceService.putDec(id,devices));
    }
}