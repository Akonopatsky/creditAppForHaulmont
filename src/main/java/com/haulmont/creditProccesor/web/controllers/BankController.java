package com.haulmont.creditProccesor.web.controllers;

import com.haulmont.creditProccesor.Exceptions.CreditProcessorException;
import com.haulmont.creditProccesor.services.BankService;
import com.haulmont.creditProccesor.services.ClientService;
import com.haulmont.creditProccesor.services.CreditService;
import com.haulmont.creditProccesor.web.dto.BankDto;
import com.haulmont.creditProccesor.web.dto.ClientDto;
import com.haulmont.creditProccesor.web.dto.CreditDto;
import com.haulmont.creditProccesor.web.dto.CreditOfferDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class BankController {
    private static final Logger logger = LoggerFactory.getLogger(BankController.class);
    private final BankService bankService;
    private final ClientService clientService;
    private final CreditService creditService;

    public BankController(BankService bankService, ClientService clientService, CreditService creditService) {
        this.bankService = bankService;
        this.clientService = clientService;
        this.creditService = creditService;
    }

    @GetMapping({"/", "/bank"})
    public String banks(Model model) {
        logger.info("bankView ");
        model.addAttribute("newBank", new BankDto());
        return "bankservice.html";
    }

    @PostMapping({"/new/bank"})
    public RedirectView createBank(@ModelAttribute BankDto newBank) throws CreditProcessorException {
        logger.info("new bank {}", newBank.getName());
        bankService.create(newBank);
        return new RedirectView("/bank", true);
    }

    @GetMapping({"/edit/bank/{id}"})
    public RedirectView editBank(
            Model model,
            @PathVariable(name = "id") String id,
            RedirectAttributes attributes,
            HttpServletRequest request
    ) throws CreditProcessorException {
        logger.info("edit bank  {}", id);
        attributes.addFlashAttribute("newName", new String());
        return new RedirectView(request.getHeader("referer"), true);
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
    public RedirectView bindBankAndClient(
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

    @GetMapping({"/delete/bank/{id}"})
    public RedirectView deleteBank(
            @PathVariable(name = "id") String id
    ) throws CreditProcessorException {
        logger.info("delete bank  {}", id);
        BankDto bank = bankService.findById(id);
        bankService.delete(bank);
        return new RedirectView("/bank", true);
    }

    @PostMapping({"/save/bank/{id}"})
    public RedirectView saveBank(
            @ModelAttribute BankDto newBank,
            @PathVariable(name = "id") String id,
            HttpServletRequest request
    ) throws CreditProcessorException {
        BankDto bank = bankService.findById(id);
        logger.info("Change bank name {} -> {}", bank.getName(), newBank.getName());
        bank.setName(newBank.getName());
        bankService.save(bank);
        return new RedirectView(request.getHeader("referer"), true);
    }

    @GetMapping({"/bank/list"})
    public String bankListView() {
        return "bank_list.html";
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
