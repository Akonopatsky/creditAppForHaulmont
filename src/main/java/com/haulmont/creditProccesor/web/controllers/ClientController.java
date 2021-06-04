package com.haulmont.creditProccesor.web.controllers;

import com.haulmont.creditProccesor.services.ClientService;
import com.haulmont.creditProccesor.web.dto.BankDto;
import com.haulmont.creditProccesor.web.dto.ClientDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ClientController {
    private final ClientService<ClientDto, BankDto> clientService;

    public ClientController(ClientService<ClientDto, BankDto> clientService) {
        this.clientService = clientService;
    }

    @GetMapping({"/client/{id}"})
    String userView(
            Model model,
            @PathVariable(name = "id") String id
    ) {
        model.addAttribute("client", clientService.findById(id));
        return "client.html";
    }
}
