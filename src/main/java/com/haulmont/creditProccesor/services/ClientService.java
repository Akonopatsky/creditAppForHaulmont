package com.haulmont.creditProccesor.services;

import com.haulmont.creditProccesor.Exceptions.CreditProcessorException;
import com.haulmont.creditProccesor.web.dto.BankDto;
import com.haulmont.creditProccesor.web.dto.ClientDto;

import java.util.List;

public interface ClientService<ClientDto, BankDto> {
    void save(ClientDto client) throws CreditProcessorException;

    ClientDto findById(Object id) throws CreditProcessorException;

    List<ClientDto> findAll();

    List<ClientDto> findByBank(String id) throws CreditProcessorException;
}
