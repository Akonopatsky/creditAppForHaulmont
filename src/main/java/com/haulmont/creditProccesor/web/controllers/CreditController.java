package com.haulmont.creditProccesor.web.controllers;

import com.haulmont.creditProccesor.Exceptions.CreditProcessorException;
import com.haulmont.creditProccesor.services.CreditService;
import com.haulmont.creditProccesor.web.dto.CreditDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CreditController {
    private final CreditService creditService;

    public CreditController(CreditService creditService) {
        this.creditService = creditService;
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
}
