package com.example.springtest.controllers;

import com.example.springtest.entity.ContractsSqlDao;
import com.example.springtest.entity.DevicesSqlDao;
import com.example.springtest.service.deviceService.impl.DeviceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
    @PutMapping("/equpments/edir/{id}")
    public ResponseEntity deliteOnlyCntrc(@PathVariable("id") int id, @RequestBody DevicesSqlDao dd){
        System.out.println(dd.getPrice());
        return ResponseEntity.ok(deviceService.deleteOnlyCntrc(id, dd.getPrice()));
    }
    @GetMapping("/equpments/view/{id}")
    public String findq(@PathVariable("id") int id){
        if(id == 0){
            return "equpments";
        }else{
            return "equpments/{id}";}
    }
    @GetMapping("/equpments/ht/{id}")
    public ResponseEntity findIdq(@PathVariable("id") int id){
        return ResponseEntity.ok(deviceService.viewId(id));
    }
    @GetMapping("/equpments/topdf")
    public String getPDF(@RequestParam("value") int value, @RequestParam("name") String name)  {
        deviceService.getPDF(value, name);
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

    @GetMapping("/equpments/topSumm")
    public ResponseEntity getTopSumm(@RequestParam("name") String name){
        return ResponseEntity.ok(deviceService.sortByPrice(name));
    }

    @GetMapping("/equpments/topCount")
    public ResponseEntity getTopCount(@RequestParam("name") String name){
        return ResponseEntity.ok(deviceService.sortByCount(name));
    }
}