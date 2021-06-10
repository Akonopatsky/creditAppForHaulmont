package com.haulmont.creditProccesor.services;

import com.haulmont.creditProccesor.Exceptions.CreditProcessorException;
import com.haulmont.creditProccesor.model.Bank;
import com.haulmont.creditProccesor.model.Client;
import com.haulmont.creditProccesor.services.mappers.BankMapper;
import com.haulmont.creditProccesor.storage.dao.BankDao;
import com.haulmont.creditProccesor.storage.dao.ClientDao;
import com.haulmont.creditProccesor.web.dto.BankDto;

import com.haulmont.creditProccesor.web.dto.ClientDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class BankServiceImpl implements BankService<BankDto> {
    private static final Logger logger = LoggerFactory.getLogger(BankServiceImpl.class);

    private final BankMapper mapper;
    private final BankDao<Bank> bankDao;
    private final ClientDao<Client, Bank> clientDao;

    public BankServiceImpl(BankMapper mapper, BankDao<Bank> bankDao, ClientDao<Client, Bank> clientDao) {
        this.mapper = mapper;
        this.bankDao = bankDao;
        this.clientDao = clientDao;
    }

    @Override
    public void save(BankDto bankDto) {
        logger.info("save bank {}", bankDto);
        bankDao.save(mapper.getNewBank(bankDto));
    }

    @Override
    public BankDto findById(Object id) throws CreditProcessorException {
        logger.info("get by id {}", id);
        return mapper.convertToDto(bankDao.findById(id));
    }

    @Override
    public List<BankDto> findAll() {
        logger.info("get all banks");
        return mapper.convertToDtoList(bankDao.findAll());
    }

    @Override
    @Transactional
    public List<BankDto> findByClient(String client_id) throws CreditProcessorException {
        return mapper.convertToDtoList(
                new ArrayList<>(clientDao.findById(client_id).getBankSet()));
    }

}
