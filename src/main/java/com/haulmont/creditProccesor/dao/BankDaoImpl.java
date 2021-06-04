package com.haulmont.creditProccesor.dao;

import com.haulmont.creditProccesor.Exceptions.CreditProcessorException;
import com.haulmont.creditProccesor.dao.Entities.BankEntity;
import com.haulmont.creditProccesor.dao.repositities.BankRepository;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Component
public class BankDaoImpl implements BankDao<BankEntity> {
    private final BankRepository bankRepository;

    public BankDaoImpl(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    @Override
    public void save(BankEntity bank) {
        bankRepository.save(bank);
    }

    @Override
    public BankEntity findById(Object id) throws CreditProcessorException {
        UUID uuid  = UUID.fromString(id.toString());
        return bankRepository.findById(uuid).orElseThrow(
                () -> new CreditProcessorException("there is no bank uuid " + uuid));
    }

    @Override
    public Set<BankEntity> findAll() {
        return new HashSet<BankEntity>((Collection<? extends BankEntity>) bankRepository.findAll());
    }
}
