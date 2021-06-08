package com.haulmont.creditProccesor.services;

import com.haulmont.creditProccesor.Exceptions.CreditProcessorException;

import java.util.List;

public interface ClientService<ClientDto, BankDto> {
    void save(ClientDto client);
    ClientDto findById(Object id) throws CreditProcessorException;
    List<ClientDto> findByBank(BankDto bank) throws CreditProcessorException;
}
