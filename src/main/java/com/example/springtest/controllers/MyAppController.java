package com.example.springtest.controllers;


import com.example.springtest.repos.ClientsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class MyAppController {

    @RequestMapping("/")
    public String index() {
        return "start";
    }

    @RequestMapping("/start")
    public String test() {
        return "start";
    }
}
