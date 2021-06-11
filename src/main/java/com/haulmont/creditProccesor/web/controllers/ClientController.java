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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class ClientController {
    private static final Logger logger = LoggerFactory.getLogger(ClientController.class);
    private final ClientService<ClientDto, BankDto> clientService;
    private final BankService<BankDto> bankService;

    public ClientController(ClientService<ClientDto, BankDto> clientService, BankService<BankDto> bankService) {
        this.clientService = clientService;
        this.bankService = bankService;
    }

    @GetMapping({"/clientService/"})
    public String clientsView(Model model) {
        logger.info("clientsView ");
        return "clientservice.html";
    }

    @GetMapping({"/client/{id}"})
    public String clientView(
            Model model,
/*            RedirectAttributes redirectAttributes,*/
            @PathVariable(name = "id") String id
    ) throws CreditProcessorException {
        logger.info("get client by id {}", id);
        ClientDto client = clientService.findById(id);
        model.addAttribute("client", client);
        List<BankDto> bankList = bankService.findByClient(client.getId());
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
    public String chooseBank(Model model,
                             @PathVariable(name = "id") String id) throws CreditProcessorException {
        logger.info("client {} choose a bank ", id);
        ClientDto client = clientService.findById(id);
        model.addAttribute("client", client);
        List<BankDto> bankList = bankService.findAll();
        model.addAttribute("bankList", bankList);
        return "chooseBank.html";
    }

    @GetMapping({"/client/{clientId}/bank/{bankId}"})
    public RedirectView clientAddClient(
            @PathVariable(name = "clientId") String clientId,
            @PathVariable(name = "bankId") String bankId
    ) throws CreditProcessorException {
        logger.info("client {} add bank {}", clientId);
        bankService.bankAddClient(bankId, clientId);
        return new RedirectView("/client/"+clientId, true);
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
