package com.haulmont.creditProccesor.services;

import com.haulmont.creditProccesor.web.dto.BankDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class BankServiceImpl implements BankService<BankDto> {
    private static final Logger logger = LoggerFactory.getLogger(BankServiceImpl.class);
    @Override
    public void save(BankDto bankDto) {

    }

    @Override
    public BankDto getById(Object id) {
        return null;
    }
}
