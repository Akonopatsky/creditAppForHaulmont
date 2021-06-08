package com.haulmont.creditProccesor.services;

import com.haulmont.creditProccesor.Exceptions.CreditProcessorException;
import com.haulmont.creditProccesor.model.Bank;
import com.haulmont.creditProccesor.services.mappers.DtoToModelMapper;
import com.haulmont.creditProccesor.storage.dao.BankDao;
import com.haulmont.creditProccesor.web.dto.BankDto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankServiceImpl implements BankService<BankDto> {
    private static final Logger logger = LoggerFactory.getLogger(BankServiceImpl.class);

    private final DtoToModelMapper mapper;
    private final BankDao<Bank> bankDao;

    public BankServiceImpl(DtoToModelMapper mapper, BankDao<Bank> bankDao) {
        this.mapper = mapper;
        this.bankDao = bankDao;
    }

    @Override
    public void save(BankDto bankDto) {
        logger.info("save bank {}", bankDto);
        bankDao.save(mapper.getNewBank(bankDto));
    }

    @Override
    public BankDto getById(Object id) throws CreditProcessorException {
        logger.info("get by id {}", id);
        return mapper.getById(bankDao.findById(id));
    }

    @Override
    public List<BankDto> getAll() {
        logger.info("get all banks");
        return mapper.getAll(bankDao.findAll());
    }
}
