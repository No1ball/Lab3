package com.example.springtest.controllers;

import com.example.springtest.entity.DevicesSqlDao;
import com.example.springtest.repos.ClientsRepo;
import com.example.springtest.repos.ContractsRepo;
import com.example.springtest.repos.DevicesRepo;
import com.example.springtest.service.deviceService.impl.DeviceServiceImpl;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.io.*;
import com.itextpdf.html2pdf.HtmlConverter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
public class deviceController {
    @Autowired
    private DeviceServiceImpl deviceService;
    public static final String FONT = "./src/main/resources/arialmt.ttf";
    @GetMapping("/newequpments")
    public ResponseEntity view(){
        return ResponseEntity.ok(deviceService.getDevices());
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
