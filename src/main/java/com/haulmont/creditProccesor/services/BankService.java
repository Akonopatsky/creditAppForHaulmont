package com.haulmont.creditProccesor.services;

import com.haulmont.creditProccesor.Exceptions.CreditProcessorException;
import com.haulmont.creditProccesor.web.dto.BankDto;

import java.util.List;

public interface BankService {
    void create(BankDto bankDto) throws CreditProcessorException;

    void save(BankDto bankDto) throws CreditProcessorException;

    BankDto findById(Object id) throws CreditProcessorException;

    List<BankDto> findAll();

    List<BankDto> findByClient(String client_id) throws CreditProcessorException;

    boolean bankAddClient(String bankId, String clientId) throws CreditProcessorException;

    void delete(BankDto bank) throws CreditProcessorException;

    void bankRemoveClient(String bankId, String clientId) throws CreditProcessorException;
}
