package com.haulmont.creditProccesor.storage.dao;

import com.haulmont.creditProccesor.Exceptions.CreditProcessorException;
import com.haulmont.creditProccesor.model.Bank;
import com.haulmont.creditProccesor.model.Client;
import com.haulmont.creditProccesor.storage.repositities.BankRepository;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class BankDaoImpl implements BankDao<Bank> {
    private final BankRepository bankRepository;

    public BankDaoImpl(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    @Override
    public void save(Bank bank) {
        bankRepository.save(bank);
    }

    @Override
    public Bank findById(Object id) throws CreditProcessorException {
        UUID uuid  = UUID.fromString(id.toString());
        return bankRepository.findById(uuid).orElseThrow(
                () -> new CreditProcessorException("there is no bank uuid " + uuid));
    }

    @Override
    public List<Bank> findAll() {
        return new ArrayList<>((Collection<? extends Bank>) bankRepository.findAll());
    }

    @Override
    public void delete(Bank bank) {
        bankRepository.delete(bank);
    }
}
