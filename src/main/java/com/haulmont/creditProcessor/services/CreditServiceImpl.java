package com.haulmont.creditProcessor.services;

import com.haulmont.creditProcessor.Exceptions.CreditProcessorException;
import com.haulmont.creditProcessor.model.Bank;
import com.haulmont.creditProcessor.model.Client;
import com.haulmont.creditProcessor.model.Credit;
import com.haulmont.creditProcessor.model.MoneyFactory;
import com.haulmont.creditProcessor.services.mappers.BankMapper;
import com.haulmont.creditProcessor.services.mappers.CreditMapper;
import com.haulmont.creditProcessor.storage.dao.BankDao;
import com.haulmont.creditProcessor.storage.dao.ClientDao;
import com.haulmont.creditProcessor.storage.dao.CreditDao;
import com.haulmont.creditProcessor.web.dto.CreditDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Service
public class CreditServiceImpl implements CreditService {
    private static final Logger logger = LoggerFactory.getLogger(CreditServiceImpl.class);

    private final BankMapper bankMapper;
    private final CreditMapper creditMapper;
    private final BankDao<Bank> bankDao;
    private final ClientDao<Client> clientDao;
    private final CreditDao<Credit> creditDao;
    private final MoneyFactory moneyFactory;

    public CreditServiceImpl(BankMapper bankMapper, CreditMapper creditMapper, BankDao<Bank> bankDao, ClientDao<Client> clientDao, CreditDao<Credit> creditDao, MoneyFactory moneyFactory) {
        this.bankMapper = bankMapper;
        this.creditMapper = creditMapper;
        this.bankDao = bankDao;
        this.clientDao = clientDao;
        this.creditDao = creditDao;
        this.moneyFactory = moneyFactory;
    }

    @Override
    @Transactional
    public void createCredit(CreditDto creditDto) throws CreditProcessorException {
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
                new ArrayList<>(bankDao.findById(id).getCreditSet()));
        return creditDtoList;
    }

    @Override
    @Transactional
    public void save(CreditDto creditDto) throws CreditProcessorException {
        Credit credit = creditDao.findById(creditDto.getId());
        credit.setCreditLimit(moneyFactory.getMoneyOf(creditDto.getCreditLimit()));
        credit.setInterestRate(creditDto.getInterestRate());
        credit.setPeriod(Period.ofMonths(creditDto.getPeriod()));
        creditDao.save(credit);
    }

    @Override
    public void delete(CreditDto creditDto) throws CreditProcessorException {
        Credit credit = creditDao.findById(creditDto.getId());
        creditDao.delete(credit);
    }
}
