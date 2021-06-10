package com.haulmont.creditProccesor.web.controllers;

import com.haulmont.creditProccesor.Exceptions.CreditProcessorException;
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
public class ClientController {
    private static final Logger logger = LoggerFactory.getLogger(ClientController.class);
    private final ClientService<ClientDto, BankDto> clientService;

    public ClientController(ClientService<ClientDto, BankDto> clientService) {
        this.clientService = clientService;
    }

    @GetMapping({"/clientService/"})
    public String clientsView(Model model) {
        logger.info("clientsView ");
        return "clientservice.html";
    }

    @GetMapping({"/client/{id}"})
    public String clientView(
            Model model,
            @PathVariable(name = "id") String id
    ) throws CreditProcessorException {
        logger.info("get client by id {}", id);
        model.addAttribute("client", clientService.findById(id));
        return "client.html";
    }

    @PostMapping({"/client/save"})
    public RedirectView clientSave(@ModelAttribute ClientDto newClient) throws CreditProcessorException {
        logger.info("new client {}", newClient);
        clientService.save(newClient);
        return new RedirectView("/clientService/", true);
    }

    @PutMapping({"/client/{id}/bank"})
    public String chooseBank(Model model, @ModelAttribute ClientDto client){
        logger.info("client {} choose a bank ", client.getId());
        return "chooseBank.html";
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
