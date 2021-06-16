package com.haulmont.creditProccesor.web.controllers;

import com.haulmont.creditProccesor.Exceptions.CreditProcessorException;
import com.haulmont.creditProccesor.services.CreditService;
import com.haulmont.creditProccesor.web.dto.ClientDto;
import com.haulmont.creditProccesor.web.dto.CreditDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CreditController {
    private static final Logger logger = LoggerFactory.getLogger(CreditController.class);
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

    @GetMapping({"/edit/credit/{id}"})
    public RedirectView editCredit(
            Model model,
            @PathVariable(name = "id") String id,
            RedirectAttributes attributes,
            HttpServletRequest request
    ) throws CreditProcessorException {
        logger.info("edit credit  {}", id);
        attributes.addFlashAttribute("editCredit", creditService.findById(id));
        return new RedirectView(request.getHeader("referer"), true);
    }

    @PostMapping({"/save/credit/{id}"})
    public RedirectView saveCredit(@ModelAttribute CreditDto editCredit,
                                   @PathVariable(name = "id") String id,
                                   HttpServletRequest request) throws CreditProcessorException {
        editCredit.setId(id);
        logger.info("edit credit {}", editCredit);
        creditService.save(editCredit);
        return new RedirectView(request.getHeader("referer"), true);
    }

    @GetMapping({"/delete/credit/{id}"})
    public RedirectView deleteClient(
            @PathVariable(name = "id") String id
    ) throws CreditProcessorException {
        logger.info("delete credit  {}", id);
        CreditDto creditDto = creditService.findById(id);
        String bankId = creditDto.getBankId();
        creditService.delete(creditDto);
        return new RedirectView("/bank/" + bankId, true);
    }

}
