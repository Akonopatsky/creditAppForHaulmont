package com.haulmont.creditProccesor.web.controllers;

import com.haulmont.creditProccesor.Exceptions.CreditProcessorException;
import com.haulmont.creditProccesor.services.ClientService;
import com.haulmont.creditProccesor.web.dto.BankDto;
import com.haulmont.creditProccesor.web.dto.ClientDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ClientController {
    private static final Logger logger = LoggerFactory.getLogger(ClientController.class);
    private final ClientService<ClientDto, BankDto> clientService;

    public ClientController(ClientService<ClientDto, BankDto> clientService) {
        this.clientService = clientService;
    }

    @GetMapping({"/client/"})
    public String clientsView(Model model) {
        return "clientservice.html";
    }

    @GetMapping({"/client/{id}"})
    public String clientView(
            Model model,
            @PathVariable(name = "id") String id
    ) throws CreditProcessorException {
        model.addAttribute("client", clientService.findById(id));
        return "client.html";
    }

    @PostMapping({"/client/save"})
    public String clientSave(@ModelAttribute ClientDto newClient) {
        logger.info("new client {}", newClient);
        clientService.save(newClient);
        return "client.html";
    }

    @ModelAttribute("newClient")
    public ClientDto getEmptyClientDto() {
        logger.info("create empty client object");
        return new ClientDto();
    }
}
