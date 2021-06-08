package com.haulmont.creditProccesor.storage.dao;

import com.haulmont.creditProccesor.Exceptions.CreditProcessorException;
import com.haulmont.creditProccesor.storage.Domain.BankData;
import com.haulmont.creditProccesor.storage.repositities.BankRepository;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Component
public class BankDaoImpl implements BankDao<BankData> {
    private final BankRepository bankRepository;

    public BankDaoImpl(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    @Override
    public void save(BankData bank) {
        bankRepository.save(bank);
    }

    @Override
    public BankData findById(Object id) throws CreditProcessorException {
        UUID uuid  = UUID.fromString(id.toString());
        return bankRepository.findById(uuid).orElseThrow(
                () -> new CreditProcessorException("there is no bank uuid " + uuid));
    }

    @Override
    public Set<BankData> findAll() {
        return new HashSet<BankData>((Collection<? extends BankData>) bankRepository.findAll());
    }
}
