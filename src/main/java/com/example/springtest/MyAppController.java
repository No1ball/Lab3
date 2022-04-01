package com.example.springtest;

import org.springframework.stereotype.Controller;
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
}
