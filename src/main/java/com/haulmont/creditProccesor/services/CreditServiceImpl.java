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
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CreditServiceImpl implements CreditService {
    private static final Logger logger = LoggerFactory.getLogger(CreditServiceImpl.class);

    private final BankMapper bankMapper;
    private final CreditMapper creditMapper;
    private final BankDao<Bank> bankDao;
    private final ClientDao<Client, Bank> clientDao;
    private final CreditDao<Credit> creditDao;

    public CreditServiceImpl(BankMapper bankMapper, CreditMapper creditMapper, BankDao<Bank> bankDao, ClientDao<Client, Bank> clientDao, CreditDao<Credit> creditDao) {
        this.bankMapper = bankMapper;
        this.creditMapper = creditMapper;
        this.bankDao = bankDao;
        this.clientDao = clientDao;
        this.creditDao = creditDao;
    }

    @Override
    @Transactional
    public void saveCredit(CreditDto creditDto) throws CreditProcessorException {
        logger.info("save credit {}", creditDto);
        Bank bank = bankDao.findById(creditDto.getBankId());
        Credit credit = creditMapper.getNewCredit(creditDto);
        bank.addCredit(credit);
        credit.setBank(bank);
        creditDao.save(credit);
        bank.addCredit(credit);

    }

    @Override
    public CreditDto findById(String id) throws CreditProcessorException {
        logger.info("find credit by id {}", id);
        return creditMapper.convertToDto(creditDao.findById(id));
    }

    @Override
    public List<CreditDto> findByBank(String id) throws CreditProcessorException {
        List<CreditDto> creditDtoList = creditMapper.convertToDtoList(
                new ArrayList<Credit>(bankDao.findById(id).getCreditSet()));
        return creditDtoList;
    }
}
