package com.haulmont.creditProccesor.web.controllers;

import com.haulmont.creditProccesor.services.BankService;
import com.haulmont.creditProccesor.web.dto.BankDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class BankController {
    private static final Logger logger = LoggerFactory.getLogger(BankController.class);
    private final BankService<BankDto> bankService;

    public BankController(BankService<BankDto> bankService) {
        this.bankService = bankService;
    }
    
    @GetMapping({"/bank/list"})
    public String bankView(Model model) {
        List<BankDto> bankList = new ArrayList<>();
        bankList.add(new BankDto("uuid1", "BankName1", Collections.singleton("client1"), Collections.singleton("credit1")));
        bankList.add(new BankDto("uuid2", "BankName2", Collections.singleton("client2"), Collections.singleton("credit2")));
        model.addAttribute("bankList", bankList);
        return "bank_list.html";
    }

    @PostMapping({"/bank/save"})
    public RedirectView bankSave(@ModelAttribute BankDto bankNew) {
        logger.info("new bank {}", bankNew.getName());
        bankService.save(bankNew);
        return new RedirectView("/", true);
    }
}
