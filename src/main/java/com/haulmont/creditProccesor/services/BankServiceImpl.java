package com.haulmont.creditProccesor.services;

import com.haulmont.creditProccesor.Exceptions.CreditProcessorException;
import com.haulmont.creditProccesor.model.Bank;
import com.haulmont.creditProccesor.model.Client;
import com.haulmont.creditProccesor.model.Credit;
import com.haulmont.creditProccesor.services.mappers.BankMapper;
import com.haulmont.creditProccesor.services.mappers.CreditMapper;
import com.haulmont.creditProccesor.storage.dao.BankDao;
import com.haulmont.creditProccesor.storage.dao.ClientDao;
import com.haulmont.creditProccesor.storage.dao.CreditDao;
import com.haulmont.creditProccesor.web.dto.BankDto;

import com.haulmont.creditProccesor.web.dto.CreditDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class BankServiceImpl implements BankService{
    private static final Logger logger = LoggerFactory.getLogger(BankServiceImpl.class);

    private final BankMapper bankMapper;
    private final CreditMapper creditMapper;
    private final BankDao<Bank> bankDao;
    private final ClientDao<Client> clientDao;
    private final CreditDao<Credit> creditDao;

    public BankServiceImpl(BankMapper bankMapper, CreditMapper creditMapper, BankDao<Bank> bankDao, ClientDao<Client> clientDao, CreditDao<Credit> creditDao) {
        this.bankMapper = bankMapper;
        this.creditMapper = creditMapper;
        this.bankDao = bankDao;
        this.clientDao = clientDao;
        this.creditDao = creditDao;
    }

    @Override
    public void save(BankDto bankDto) {
        logger.info("save bank {}", bankDto);
        bankDao.save(bankMapper.getNewBank(bankDto));
    }

    @Override
    public BankDto findById(Object id) throws CreditProcessorException {
        logger.info("get by id {}", id);
        return bankMapper.convertToDto(bankDao.findById(id));
    }

    @Override
    public List<BankDto> findAll() {
        logger.info("get all banks");
        return bankMapper.convertToDtoList(bankDao.findAll());
    }

    @Override
    @Transactional
    public List<BankDto> findByClient(String client_id) throws CreditProcessorException {
        return bankMapper.convertToDtoList(
                new ArrayList<>(clientDao.findById(client_id).getBankSet()));
    }

    @Override
    @Transactional
    public boolean bankAddClient(String bankId, String clientId) throws CreditProcessorException {
        Bank bank = bankDao.findById(bankId);
        Client client = clientDao.findById(clientId);
        boolean result = bank.addClient(client);
        bankDao.save(bank);
        return result;
    }


}
