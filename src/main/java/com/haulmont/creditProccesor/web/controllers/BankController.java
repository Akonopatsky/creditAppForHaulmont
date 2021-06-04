package com.haulmont.creditProccesor.web.controllers;

import com.haulmont.creditProccesor.Exceptions.CreditProcessorException;
import com.haulmont.creditProccesor.services.BankService;
import com.haulmont.creditProccesor.web.dto.BankDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Set;

@Controller
public class BankController {
    private static final Logger logger = LoggerFactory.getLogger(BankController.class);
    private final BankService<BankDto> bankService;

    public BankController(BankService<BankDto> bankService) {
        this.bankService = bankService;
    }

    @GetMapping({"/bank/list"})
    public String bankListView() {
        return "bank_list.html";
    }

    @PostMapping({"/bank/save"})
    public RedirectView bankSave(@ModelAttribute BankDto bankNew) {
        logger.info("new bank {}", bankNew.getName());
        bankService.save(bankNew);
        return new RedirectView("/", true);
    }

    @GetMapping({"/bank/{id}}"})
    public String bankView(
            Model model,
            @PathVariable(name = "id") String id
    ) throws CreditProcessorException {
        BankDto bank = bankService.getById(id);
        model.addAttribute("bank", bank);
        return "bank.html";
    }

    @ModelAttribute("bankList")
    Set<BankDto> getAllBanks() {
        return bankService.getAll();
    }
}
