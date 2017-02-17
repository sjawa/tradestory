package de.app.tradestory.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/partners")
public class PartnerController {

    @GetMapping
    public String show() {

        return "tradestory/show";
    }

    @RequestMapping("/new")
    @GetMapping
    public String newPartner(){
        return "tradestory/new";
    }

}
