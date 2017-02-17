package de.app.partner.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
