package com.haulmont.creditProccesor.services;

import com.haulmont.creditProccesor.Exceptions.CreditProcessorException;

import java.util.Set;

public interface BankService<BankDto> {
    void save(BankDto bankDto);

    BankDto getById(Object id) throws CreditProcessorException;

    Set<com.haulmont.creditProccesor.web.dto.BankDto> getAll();
}
