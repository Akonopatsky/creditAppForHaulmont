package com.haulmont.creditProccesor.web.controllers;

import com.haulmont.creditProccesor.Exceptions.CreditProcessorException;
import com.haulmont.creditProccesor.services.BankService;
import com.haulmont.creditProccesor.services.ClientService;
import com.haulmont.creditProccesor.services.CreditService;
import com.haulmont.creditProccesor.web.dto.BankDto;
import com.haulmont.creditProccesor.web.dto.ClientDto;
import com.haulmont.creditProccesor.web.dto.CreditDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class BankController {
    private static final Logger logger = LoggerFactory.getLogger(BankController.class);
    private final BankService<BankDto> bankService;
    private final ClientService<ClientDto> clientService;
    private final CreditService<CreditDto> creditService;

    public BankController(BankService<BankDto> bankService, ClientService clientService, CreditService<CreditDto> creditService) {
        this.bankService = bankService;
        this.clientService = clientService;
        this.creditService = creditService;
    }

    @GetMapping({"/"})
    public String mainView(Model model, @ModelAttribute("bankNew") BankDto bankNew) {
        return "index.html";
    }

    @GetMapping({"/bankService"})
    public String clientsView(Model model) {
        logger.info("bankView ");
        model.addAttribute("newBank", new BankDto());
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
        List<CreditDto> creditList = creditService.findByBank(id);
        model.addAttribute("clientList", clientList);
        model.addAttribute("creditList", creditList);
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

    @GetMapping({"/bind/bank/{bankId}/client/{clientId}"})
    public RedirectView bind1BankAndClient(
            @PathVariable(name = "bankId") String bankId,
            @PathVariable(name = "clientId") String clientId,
            HttpServletRequest request
    ) throws CreditProcessorException {
        logger.info("bank {} add client {}", bankId);
        bankService.bankAddClient(bankId, clientId);
        return new RedirectView("/bank/" + bankId, true);
    }

    @GetMapping({"/create/credit/bank/{bankId}"})
    public String createCredit(Model model, @PathVariable(name = "bankId") String bankId) throws CreditProcessorException {
        logger.info("create credit page");
        BankDto bank = bankService.findById(bankId);
        model.addAttribute("bank", bank);
        return "newCredit.html";
    }

    @PostMapping({"/save/credit/bank/{bankId}"})
    public RedirectView creditSave(
            @ModelAttribute CreditDto newCredit,
            @PathVariable(name = "bankId") String bankId
    ) throws CreditProcessorException {
        newCredit.setBankId(bankId);
        logger.info("new credit {} , {}, {}", newCredit);
        creditService.saveCredit(newCredit);
        return new RedirectView("/bank/" + bankId, true);
    }

    @GetMapping({"/credit/{id}"})
    public String creditView(
            Model model,
            @PathVariable(name = "id") String id
    ) throws CreditProcessorException {
        CreditDto credit = creditService.findById(id);
        model.addAttribute("credit", credit);
        return "credit.html";
    }

    @ModelAttribute("bankList")
    List<BankDto> getAllBanks() {
        return bankService.findAll();
    }

    @ModelAttribute("newBank")
    BankDto getEmptyBank() {
        return new BankDto();
    }

    @ModelAttribute("newCredit")
    CreditDto getEmptyCredit() {
        return new CreditDto();
    }
}
