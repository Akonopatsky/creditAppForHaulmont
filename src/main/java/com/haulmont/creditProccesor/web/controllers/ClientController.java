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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ClientController {
    private static final Logger logger = LoggerFactory.getLogger(ClientController.class);
    private final ClientService<ClientDto> clientService;
    private final BankService<BankDto> bankService;
    private final CreditService<CreditDto> creditService;


    public ClientController(ClientService<ClientDto> clientService, BankService<BankDto> bankService, CreditService<CreditDto> creditService) {
        this.clientService = clientService;
        this.bankService = bankService;
        this.creditService = creditService;
    }

    @GetMapping({"/clientService"})
    public String clientsView(Model model) {
        logger.info("clientsView ");
        return "clientservice.html";
    }

    @GetMapping({"/client/{id}"})
    public String clientView(
            Model model,
            @PathVariable(name = "id") String id,
            RedirectAttributes attributes
    ) throws CreditProcessorException {
        logger.info("get client by id {}", id);
        ClientDto client = clientService.findById(id);
        List<BankDto> bankList = bankService.findByClient(client.getId());
        model.addAttribute("client", client);
        model.addAttribute("bankList", bankList);
        return "client.html";
    }

    @PostMapping({"/client/save"})
    public RedirectView clientSave(@ModelAttribute ClientDto newClient) throws CreditProcessorException {
        logger.info("new client {}", newClient);
        clientService.save(newClient);
        return new RedirectView("/clientService/", true);
    }

    @GetMapping({"/client/{id}/bank"})
    public String chooseBank(
            Model model,
            @PathVariable(name = "id") String id
    ) throws CreditProcessorException {
        logger.info("client {} choose a bank ", id);
        ClientDto client = clientService.findById(id);
        model.addAttribute("client", client);
        List<BankDto> bankList = bankService.findAll();
        model.addAttribute("bankList", bankList);
        return "chooseBank.html";
    }

    @GetMapping({"/bind/client/{clientId}/bank/{bankId}"})
    public RedirectView clientAddBank(
            @PathVariable(name = "clientId") String clientId,
            @PathVariable(name = "bankId") String bankId
    ) throws CreditProcessorException {
        logger.info("client {} add bank {}", clientId);
        bankService.bankAddClient(bankId, clientId);
        return new RedirectView("/client/" + clientId, true);
    }


    @GetMapping({"/client/{clientId}/bank/{bankId}/credit"})
    public RedirectView getAvailableCredit(
            @PathVariable(name = "clientId") String clientId,
            @PathVariable(name = "bankId") String bankId,
            RedirectAttributes attributes,
            HttpServletRequest request
    ) throws CreditProcessorException {
        logger.info("find available credit of bank {} fo client {}", bankId, clientId);
        ClientDto client = clientService.findById(clientId);
        List<CreditDto> creditList = creditService.findByBank(bankId);
        BankDto bank = bankService.findById(bankId);
        attributes.addFlashAttribute("bank", bank);
        attributes.addFlashAttribute("client", client);
        attributes.addFlashAttribute("creditList", creditList);
        return new RedirectView(request.getHeader("referer"), true);
    }

    @GetMapping({"/client/{clientId}/credit/{creditId}"})
    public RedirectView chooseCredit(
            @PathVariable(name = "clientId") String clientId,
            @PathVariable(name = "creditId") String creditId,
            RedirectAttributes attributes,
            HttpServletRequest request
    ) throws CreditProcessorException {
        attributes.addFlashAttribute("choosenCredit", creditService.findById(creditId));
        return new RedirectView(request.getHeader("referer"), true);
    }

    @ModelAttribute("newClient")
    public ClientDto getEmptyClientDto() {
        logger.info("create empty client object");
        return new ClientDto();
    }

    @ModelAttribute("clientList")
    public List<ClientDto> getClientList() {
        logger.info("get client list");
        return clientService.findAll();
    }
}
