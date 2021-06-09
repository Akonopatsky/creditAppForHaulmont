
package com.haulmont.creditProccesor.services;

import com.haulmont.creditProccesor.Exceptions.CreditProcessorException;
import com.haulmont.creditProccesor.model.Bank;
import com.haulmont.creditProccesor.model.Client;
import com.haulmont.creditProccesor.services.mappers.ClientMapper;
import com.haulmont.creditProccesor.storage.dao.BankDao;
import com.haulmont.creditProccesor.storage.dao.ClientDao;
import com.haulmont.creditProccesor.web.dto.BankDto;
import com.haulmont.creditProccesor.web.dto.ClientDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService<ClientDto, BankDto> {
    private static final Logger logger = LoggerFactory.getLogger(ClientServiceImpl.class);

    private final ClientMapper mapper;
    private final BankDao<Bank> bankDao;
    private final ClientDao<Client, Bank> clientDao;

    public ClientServiceImpl(ClientMapper mapper, BankDao<Bank> bankDao, ClientDao<Client, Bank> clientDao) {
        this.mapper = mapper;
        this.bankDao = bankDao;
        this.clientDao = clientDao;
    }

    @Override
    public void save(ClientDto clientDto) throws CreditProcessorException {
        logger.info("save client {}", clientDto);
        if (clientDao.findByPassportNumber(clientDto.getPassportNumber()).isPresent()) {
            throw new CreditProcessorException("passport number " + clientDto.getPassportNumber() + " is present in base");
        }
        clientDao.save(mapper.getNewClient(clientDto));
    }

    @Override
    public ClientDto findById(Object id) throws CreditProcessorException {
        logger.info("find client by id {}", id);
        return mapper.getById(clientDao.findById(id));
    }
    

    @Override
    public List<ClientDto> findByBank(BankDto bankDto) throws CreditProcessorException {
        logger.info("find all client of bank {}", bankDto);
        return mapper.getAll(clientDao.findByBank(bankDao.findById(bankDto.getId())));
    }

    @Override
    public List<ClientDto> findAll() {
        return mapper.getAll((clientDao.findAll()));
    }
}

