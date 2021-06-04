package com.haulmont.creditProccesor.web.controllers;

import com.haulmont.creditProccesor.services.BankService;
import com.haulmont.creditProccesor.web.dto.BankDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Set;

@Controller
public class MainController {

    private static final Logger logger = LoggerFactory.getLogger(BankController.class);
    private final BankService<BankDto> bankService;

    public MainController(BankService<BankDto> bankService) {
        this.bankService = bankService;
    }

    @GetMapping({"/"})
    public String mainView(
            Model model,
            @ModelAttribute("bankNew") BankDto bankNew
    ) {
        return "index.html";
    }

    @ModelAttribute("bankList")
    Set<BankDto> getAllBanks() {
        return bankService.getAll();
    }
}
