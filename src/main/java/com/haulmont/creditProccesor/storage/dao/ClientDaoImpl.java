package com.haulmont.creditProccesor.storage.dao;

import com.haulmont.creditProccesor.Exceptions.CreditProcessorException;

import com.haulmont.creditProccesor.model.Bank;
import com.haulmont.creditProccesor.model.Client;
import com.haulmont.creditProccesor.storage.repositities.ClientRepository;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ClientDaoImpl implements ClientDao<Client> {
    private final ClientRepository clientRepository;

    public ClientDaoImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public void save(Client client) {
        clientRepository.save(client);
    }

    @Override
    public Client findById(Object id) throws CreditProcessorException {
        UUID uuid  = UUID.fromString(id.toString());
        return clientRepository.findById(uuid).orElseThrow(
                () -> new CreditProcessorException("there is no client uuid " + uuid));
    }

    @Override
    public Optional<Client> findByPassportNumber(String passport) {
        return clientRepository.findByPassportNumber(passport);
    }

    @Override
    public List<Client> findAll() {
        return (List<Client>)clientRepository.findAll();
    }

    @Override
    public void delete(Client client) {
        Set<Bank> bankSet = new HashSet<>(client.getBankSet());
        bankSet.forEach(client::removeBank);
        clientRepository.save(client);
        clientRepository.delete(client);
    }

}
