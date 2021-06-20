package com.haulmont.creditProcessor.web.controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class AdviceController {

    @ExceptionHandler({Exception.class, Error.class})
    public ModelAndView handle(Exception ex) {
        ModelAndView mav = new ModelAndView("errorView");
        mav.addObject("error", ex);
        return mav;
    }
}
