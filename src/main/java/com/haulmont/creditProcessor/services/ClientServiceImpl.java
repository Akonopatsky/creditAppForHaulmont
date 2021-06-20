
package com.haulmont.creditProcessor.services;

import com.haulmont.creditProcessor.Exceptions.CreditProcessorException;
import com.haulmont.creditProcessor.model.Bank;
import com.haulmont.creditProcessor.model.Client;
import com.haulmont.creditProcessor.services.mappers.ClientMapper;
import com.haulmont.creditProcessor.storage.dao.BankDao;
import com.haulmont.creditProcessor.storage.dao.ClientDao;
import com.haulmont.creditProcessor.web.dto.ClientDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {
    private static final Logger logger = LoggerFactory.getLogger(ClientServiceImpl.class);

    private final ClientMapper mapper;
    private final BankDao<Bank> bankDao;
    private final ClientDao<Client> clientDao;

    public ClientServiceImpl(ClientMapper mapper, BankDao<Bank> bankDao, ClientDao<Client> clientDao) {
        this.mapper = mapper;
        this.bankDao = bankDao;
        this.clientDao = clientDao;
    }

    @Override
    public void create(ClientDto clientDto) {
        logger.info("create client {}", clientDto);
        clientDao.save(mapper.getNewClient(clientDto));
    }

    @Override
    public void save(ClientDto clientDto) throws CreditProcessorException {
        logger.info("save client {}", clientDto);
        Client client = clientDao.findById(clientDto.getId());
        client.setName(clientDto.getName());
        client.setEmail(clientDto.getEmail());
        client.setPassportNumber(clientDto.getPassportNumber());
        client.setPhoneNumber(clientDto.getPhoneNumber());
        clientDao.save(client);
    }

    @Override
    public ClientDto findById(Object id) throws CreditProcessorException {
        logger.info("find client by id {}", id);
        return mapper.convertToDto(clientDao.findById(id));
    }

    @Override
    public List<ClientDto> findAll() {
        return mapper.convertToDtoList((clientDao.findAll()));
    }

    @Override
    @Transactional()
    public List<ClientDto> findByBank(String id) throws CreditProcessorException {
        return mapper.convertToDtoList(
                new ArrayList<>(bankDao.findById(id).getClientSet()));
    }

    @Override
    @Transactional
    public void delete(ClientDto clientDto) throws CreditProcessorException {
        Client client = clientDao.findById(clientDto.getId());
        clientDao.delete(client);

    }


}

