package com.haulmont.creditProccesor.web.controllers;

import com.haulmont.creditProccesor.Exceptions.CreditProcessorException;
import com.haulmont.creditProccesor.model.PayStrategyRegistry;
import com.haulmont.creditProccesor.services.BankService;
import com.haulmont.creditProccesor.services.ClientService;
import com.haulmont.creditProccesor.services.CreditOfferService;
import com.haulmont.creditProccesor.services.CreditService;
import com.haulmont.creditProccesor.web.dto.BankDto;
import com.haulmont.creditProccesor.web.dto.ClientDto;
import com.haulmont.creditProccesor.web.dto.CreditOfferDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class CreditOfferController {
    private static final Logger logger = LoggerFactory.getLogger(CreditOfferController.class);
    private final ClientService clientService;
    private final BankService bankService;
    private final CreditService creditService;
    private final CreditOfferService creditOfferService;
    private final PayStrategyRegistry payStrategyRegistry;

    public CreditOfferController(ClientService clientService, BankService bankService, CreditService creditService, CreditOfferService creditOfferService, PayStrategyRegistry payStrategyRegistry) {
        this.clientService = clientService;
        this.bankService = bankService;
        this.creditService = creditService;
        this.creditOfferService = creditOfferService;
        this.payStrategyRegistry = payStrategyRegistry;
    }

    @GetMapping({"/offer/{id}"})
    public String offerView(
            Model model,
            @PathVariable(name = "id") String id
    ) throws CreditProcessorException {
        logger.info("get offer by id {}", id);
        CreditOfferDto offer = creditOfferService.findById(id);
        model.addAttribute("offer", offer);
        return "creditOffer.html";
    }

    @GetMapping({"/delete/offer/{id}"})
    public RedirectView deleteOffer(
            Model model,
            @PathVariable(name = "id") String id
    ) throws CreditProcessorException {
        logger.info("delete offer id {}", id);
        CreditOfferDto offer = creditOfferService.findById(id);
        String clientId = offer.getClientId();
        creditOfferService.delete(offer);
        return new RedirectView("/client/" + clientId, true);
    }

}
