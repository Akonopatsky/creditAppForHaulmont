package com.haulmont.creditProcessor.web.controllers;

import com.haulmont.creditProcessor.Exceptions.CreditProcessorException;
import com.haulmont.creditProcessor.model.PayStrategy;
import com.haulmont.creditProcessor.model.PayStrategyRegistry;
import com.haulmont.creditProcessor.services.BankService;
import com.haulmont.creditProcessor.services.ClientService;
import com.haulmont.creditProcessor.services.CreditOfferService;
import com.haulmont.creditProcessor.services.CreditService;
import com.haulmont.creditProcessor.web.dto.CreditOfferDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class CreditOfferController {
    private static final Logger logger = LoggerFactory.getLogger(CreditOfferController.class);
    private final ClientService clientService;
    private final BankService bankService;
    private final CreditService creditService;
    private final CreditOfferService creditOfferService;
    private final PayStrategyRegistry<String, PayStrategy> payStrategyRegistry;

    public CreditOfferController(ClientService clientService,
                                 BankService bankService,
                                 CreditService creditService,
                                 CreditOfferService creditOfferService,
                                 PayStrategyRegistry<String, PayStrategy> payStrategyRegistry) {
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
