package com.haulmont.creditProccesor.services;

import com.haulmont.creditProccesor.web.dto.BankDto;
import com.haulmont.creditProccesor.web.dto.ClientDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService<ClientDto, BankDto> {
    private static final Logger logger = LoggerFactory.getLogger(ClientServiceImpl.class);
    @Override
    public void save(ClientDto client) {
        logger.info("save client {}", client);
    }

    @Override
    public ClientDto findById(Object id) {
        logger.info("find client by id {}", id);
        return null;
    }

    @Override
    public List<ClientDto> findByBank(BankDto bank) {
        logger.info("find all client of bank {}", bank);
        return null;
    }
}
