package com.haulmont.creditProccesor.services;

import com.haulmont.creditProccesor.Exceptions.CreditProcessorException;
import com.haulmont.creditProccesor.web.dto.ClientDto;

import java.util.List;

public interface BankService<BankDto> {
    void save(BankDto bankDto);

    BankDto findById(Object id) throws CreditProcessorException;

    List<BankDto> findAll();

    List<BankDto> findByClient(ClientDto client) throws CreditProcessorException;
}
