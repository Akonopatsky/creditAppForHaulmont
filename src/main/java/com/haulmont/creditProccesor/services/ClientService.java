package com.haulmont.creditProccesor.services;

import java.util.List;

public interface ClientService<ClientDto, BankDto> {
    void save(ClientDto client);
    ClientDto findById(Object id);
    List<ClientDto> findByBank(BankDto bank);
}
