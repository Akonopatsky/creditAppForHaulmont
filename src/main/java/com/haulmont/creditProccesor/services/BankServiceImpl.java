package com.haulmont.creditProccesor.services;

import com.haulmont.creditProccesor.web.dto.BankDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankServiceImpl implements BankService<BankDto> {
    private static final Logger logger = LoggerFactory.getLogger(BankServiceImpl.class);
    @Override
    public void save(BankDto bank) {
        logger.info("save bank {}", bank);

    }

    @Override
    public BankDto getById(Object id) {

        logger.info("get by id {}", id);
        return null;
    }

    @Override
    public List<BankDto> getAll() {
        logger.info("get all banks");
        return null;
    }
}
