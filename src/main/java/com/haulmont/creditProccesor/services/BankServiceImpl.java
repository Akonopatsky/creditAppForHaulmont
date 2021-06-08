package com.haulmont.creditProccesor.services;

import com.haulmont.creditProccesor.Exceptions.CreditProcessorException;
import com.haulmont.creditProccesor.storage.dao.BankDao;
import com.haulmont.creditProccesor.storage.Domain.BankData;
import com.haulmont.creditProccesor.web.dto.BankDto;
import com.haulmont.creditProccesor.services.mappers.DtoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class BankServiceImpl implements BankService<BankDto> {
    private static final Logger logger = LoggerFactory.getLogger(BankServiceImpl.class);

    private final DtoMapper mapper;
    private final BankDao<BankData> bankDao;

    public BankServiceImpl(DtoMapper mapper, BankDao<BankData> bankDao) {
        this.mapper = mapper;
        this.bankDao = bankDao;
    }

    @Override
    public void save(BankDto bankDto) {
        logger.info("save bank {}", bankDto);
        bankDao.save(mapper.saveForm(bankDto));
    }

    @Override
    public BankDto getById(Object id) throws CreditProcessorException {
        logger.info("get by id {}", id);
        return mapper.findById(bankDao.findById(id));
    }

    @Override
    public Set<BankDto> getAll() {
        logger.info("get all banks");
        return mapper.findAll(bankDao.findAll());
    }
}
