package com.haulmont.creditProccesor.services;

public interface CreditService<CreditDto, BankDto> {

    void save(CreditDto credit);

    CreditDto findAllByBank(BankDto bank);

    CreditDto findById(Object id);
}
