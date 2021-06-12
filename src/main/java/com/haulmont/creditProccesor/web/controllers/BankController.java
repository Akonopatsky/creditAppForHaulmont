package com.haulmont.creditProccesor.web.controllers;

import com.haulmont.creditProccesor.Exceptions.CreditProcessorException;
import com.haulmont.creditProccesor.services.BankService;
import com.haulmont.creditProccesor.services.ClientService;
import com.haulmont.creditProccesor.web.dto.BankDto;
import com.haulmont.creditProccesor.web.dto.ClientDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class BankController {
    private static final Logger logger = LoggerFactory.getLogger(BankController.class);
    private final BankService<BankDto> bankService;
    private final ClientService<ClientDto, BankDto> clientService;

    public BankController(BankService<BankDto> bankService, ClientService clientService) {
        this.bankService = bankService;
        this.clientService = clientService;
    }

    @GetMapping({"/"})
    public String mainView(
            Model model,
            @ModelAttribute("bankNew") BankDto bankNew
    ) {
        return "index.html";
    }

    @GetMapping({"/bankService"})
    public String clientsView(Model model) {
        logger.info("bankView ");
        return "bankservice.html";
    }

    @GetMapping({"/bank/list"})
    public String bankListView() {
        return "bank_list.html";
    }

    @PostMapping({"/bank/save"})
    public RedirectView bankSave(@ModelAttribute BankDto newBank) {
        logger.info("new bank {}", newBank.getName());
        bankService.save(newBank);
        return new RedirectView("/bankService", true);
    }

    @GetMapping({"/bank/{id}"})
    public String bankView(
            Model model,
            @PathVariable(name = "id") String id
    ) throws CreditProcessorException {
        BankDto bank = bankService.findById(id);
        model.addAttribute("bank", bank);
        List<ClientDto> clientList = clientService.findByBank(id);
        model.addAttribute("clientList", clientList);
        return "bank.html";
    }

    @GetMapping({"/bank/{id}/client"})
    public String chooseClient(
            Model model,
            @PathVariable(name = "id") String id
    ) throws CreditProcessorException {
        BankDto bank = bankService.findById(id);
        model.addAttribute("bank", bank);
        List<ClientDto> clientList = clientService.findAll();
        model.addAttribute("clientList", clientList);
        return "chooseClient.html";
    }

    @GetMapping({"/bank/{bankId}/client/{clientId}"})
    public RedirectView bankAddClient(
            @PathVariable(name = "bankId") String bankId,
            @PathVariable(name = "clientId") String clientId
    ) throws CreditProcessorException {
        logger.info("bank {} add client {}", bankId);
        bankService.bankAddClient(bankId, clientId);
        return new RedirectView("/bank/"+bankId, true);
    }

    @ModelAttribute("bankList")
    List<BankDto> getAllBanks() {
        return bankService.findAll();
    }

    @ModelAttribute("newBank")
    BankDto getEmptyBank() {
        return new BankDto();
    }
}
