package com.haulmont.creditProccesor.web.controllers;

import com.haulmont.creditProccesor.web.dto.BankDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class MainController {
    @GetMapping({"/"})
    public String mainView(
            Model model,
            @ModelAttribute("bankNew") BankDto bankNew
    ) {
        return "index.html";
    }
}
