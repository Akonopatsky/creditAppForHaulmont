package com.haulmont.creditProccesor.services;

import com.haulmont.creditProccesor.Exceptions.CreditProcessorException;

import java.util.List;

public interface BankService<BankDto> {
    void save(BankDto bankDto);

    BankDto findById(Object id) throws CreditProcessorException;

    List<BankDto> findAll();

    List<BankDto> findByClient(String client_id) throws CreditProcessorException;

    boolean bankAddClient(String bankId, String clientId) throws CreditProcessorException;

}
