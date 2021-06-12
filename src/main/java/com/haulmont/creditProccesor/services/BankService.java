package com.haulmont.creditProccesor.services;

import com.haulmont.creditProccesor.Exceptions.CreditProcessorException;
import com.haulmont.creditProccesor.web.dto.CreditDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BankService<BankDto> {
    void save(BankDto bankDto);

    BankDto findById(Object id) throws CreditProcessorException;

    List<BankDto> findAll();

    List<BankDto> findByClient(String client_id) throws CreditProcessorException;

    boolean bankAddClient(String bankId, String clientId) throws CreditProcessorException;

    List<CreditDto> findByBank(String id) throws CreditProcessorException;

    void saveCredit(CreditDto newCredit) throws CreditProcessorException;

    CreditDto findCreditById(String id) throws CreditProcessorException;
}
