package com.haulmont.creditProccesor.storage.dao;

import com.haulmont.creditProccesor.Exceptions.CreditProcessorException;
import com.haulmont.creditProccesor.storage.Domain.ClientData;
import com.haulmont.creditProccesor.storage.repositities.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClientDaoImpl implements ClientDao<ClientData> {
    private final ClientRepository clientRepository;

    public ClientDaoImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public void save(ClientData client) {
        clientRepository.save(client);
    }

    @Override
    public ClientData findById(Object id) throws CreditProcessorException {
        UUID uuid  = UUID.fromString(id.toString());
        return clientRepository.findById(uuid).orElseThrow(
                () -> new CreditProcessorException("there is no client uuid " + uuid));
    }

    @Override
    public List<ClientData> findAll() {
        return (List<ClientData>)clientRepository.findAll();
    }
}
