package com.haulmont.creditProccesor.storage.dao;

import com.haulmont.creditProccesor.Exceptions.CreditProcessorException;

import com.haulmont.creditProccesor.model.Client;
import com.haulmont.creditProccesor.storage.repositities.ClientRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

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
    public List<Client> findAll() {
        return (List<Client>)clientRepository.findAll();
    }
}
