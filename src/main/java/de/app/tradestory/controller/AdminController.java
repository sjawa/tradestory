package de.app.tradestory.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class AdminController {


    public AdminController(){

    }

    @GetMapping
    public String show(){
        return "index";

    }
}
