package com.haulmont.creditProccesor.services;

import com.haulmont.creditProccesor.web.dto.BankDto;
import com.haulmont.creditProccesor.web.dto.CreditDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

@Controller
public class CreditServiceImpl implements CreditService<CreditDto, BankDto> {
    private static final Logger logger = LoggerFactory.getLogger(CreditServiceImpl.class);
    @Override
    public void save(CreditDto credit) {

    }

    @Override
    public CreditDto findAllByBank(BankDto bank) {
        return null;
    }

    @Override
    public CreditDto findById(Object id) {
        return null;
    }
}
