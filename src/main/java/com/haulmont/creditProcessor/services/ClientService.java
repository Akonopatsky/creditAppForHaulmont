package com.haulmont.creditProcessor.services;

import com.haulmont.creditProcessor.Exceptions.CreditProcessorException;
import com.haulmont.creditProcessor.web.dto.ClientDto;

import java.util.List;

public interface ClientService {
    void save(ClientDto client) throws CreditProcessorException;

    ClientDto findById(Object id) throws CreditProcessorException;

    List<ClientDto> findAll();

    List<ClientDto> findByBank(String id) throws CreditProcessorException;

    void delete(ClientDto clientDto) throws CreditProcessorException;

    void create(ClientDto clientDto) throws CreditProcessorException;
}
