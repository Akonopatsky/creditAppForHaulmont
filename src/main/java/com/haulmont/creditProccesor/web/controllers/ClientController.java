package com.haulmont.creditProccesor.web.controllers;

import com.haulmont.creditProccesor.Exceptions.CreditProcessorException;
import com.haulmont.creditProccesor.model.PayStrategyRegistry;
import com.haulmont.creditProccesor.services.*;
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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
public class ClientController {
    private static final Logger logger = LoggerFactory.getLogger(ClientController.class);
    private final ClientService clientService;
    private final BankService bankService;
    private final CreditService creditService;
    private final CreditOfferService creditOfferService;
    private final PayStrategyRegistry payStrategyRegistry;


    public ClientController(ClientService clientService, BankService bankService, CreditService creditService, CreditOfferService creditOfferService, PayStrategyRegistry payStrategyRegistry) {
        this.clientService = clientService;
        this.bankService = bankService;
        this.creditService = creditService;
        this.creditOfferService = creditOfferService;
        this.payStrategyRegistry = payStrategyRegistry;
    }

    @GetMapping({"/client"})
    public String clientsView(Model model) {
        logger.info("clientsView ");
        model.addAttribute("editClient", new ClientDto());
        return "clientService.html";
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
        List<CreditOfferDto> offerList = creditOfferService.findByClient(client);
        model.addAttribute("client", client);
        model.addAttribute("bankList", bankList);
        model.addAttribute("offerList", offerList);
        return "client.html";
    }

    @PostMapping({"/new/client"})
    public RedirectView createClient(@ModelAttribute ClientDto editClient) throws CreditProcessorException {
        logger.info("new client {}", editClient);
        clientService.create(editClient);
        return new RedirectView("/client", true);
    }

    @PostMapping({"/save/client/{id}"})
    public RedirectView clientSave(@ModelAttribute ClientDto editClient,
                                   @PathVariable(name = "id") String id) throws CreditProcessorException {
        editClient.setId(id);
        logger.info("new client {}", editClient);
        clientService.save(editClient);
        return new RedirectView("/client", true);
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
        attributes.addFlashAttribute("payStrategyList", payStrategyRegistry.getAllKeys());
        return new RedirectView(request.getHeader("referer"), true);
    }

    @PostMapping({"/save/offer/client/{clientId}/credit/{creditId}"})
    public RedirectView offerSave(
            @ModelAttribute CreditOfferDto offer,
            @PathVariable(name = "clientId") String clientId,
            @PathVariable(name = "creditId") String creditId,
            HttpServletRequest request
    ) throws CreditProcessorException {
        logger.info("new credit offer {}", offer);
        offer.setClientId(clientId);
        offer.setCreditId(creditId);
        creditOfferService.save(offer);
        return new RedirectView(request.getHeader("referer"), true);
    }

    @GetMapping({"/delete/client/{id}"})
    public RedirectView deleteClient(
            @PathVariable(name = "id") String id
    ) throws CreditProcessorException {
        logger.info("delete client  {}", id);
        ClientDto clientDto = clientService.findById(id);
        clientService.delete(clientDto);
        return new RedirectView("/client", true);
    }


    @GetMapping({"/edit/client/{id}"})
    public RedirectView editBank(
            Model model,
            @PathVariable(name = "id") String id,
            RedirectAttributes attributes,
            HttpServletRequest request
    ) throws CreditProcessorException {
        logger.info("edit client  {}", id);
        attributes.addFlashAttribute("editClient", new ClientDto());
        return new RedirectView(request.getHeader("referer"), true);
    }


    @ModelAttribute("strategyList")
    public List<String> strategyList() {
        return new ArrayList<>(payStrategyRegistry.getAllKeys());
    }

    @ModelAttribute("offer")
    public CreditOfferDto getEmptyCreditOfferDto() {
        logger.info("create empty client object");
        CreditOfferDto offer = new CreditOfferDto();
        offer.setBeginDate(LocalDate.now());
        return offer;
    }

    @ModelAttribute("clientList")
    public List<ClientDto> getClientList() {
        logger.info("get client list");
        return clientService.findAll();
    }
}
