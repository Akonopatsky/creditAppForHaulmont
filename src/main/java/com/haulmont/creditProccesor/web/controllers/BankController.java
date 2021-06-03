package com.haulmont.creditProccesor.web.controllers;

import com.haulmont.creditProccesor.web.dto.BankDto;
import org.hibernate.mapping.Collection;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collections;
import java.util.HashSet;

@Controller
public class BankController {
    @GetMapping({"/"})
    public String beankView(Model model) {
        BankDto bankDto = new BankDto("e324234", Collections.singleton("client1"), Collections.singleton("credit1"));
        model.addAttribute("bank", bankDto);
        return "bank.html";
    }
}
