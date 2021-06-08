package com.haulmont.creditProccesor.storage.dao;

import com.haulmont.creditProccesor.Exceptions.CreditProcessorException;

import com.haulmont.creditProccesor.model.Credit;
import com.haulmont.creditProccesor.storage.repositities.CreditRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
@Component
public class CreditDaoImpl implements CreditDao<Credit> {
    private final CreditRepository creditRepository;

    public CreditDaoImpl(CreditRepository creditRepository) {
        this.creditRepository = creditRepository;
    }

    @Override
    public void save(Credit credit) {
        creditRepository.save(credit);
    }

    @Override
    public Credit findById(Object id) throws CreditProcessorException {
        UUID uuid  = UUID.fromString(id.toString());
        return creditRepository.findById(uuid).orElseThrow(
                () -> new CreditProcessorException("there is no credit uuid " + uuid));
    }

    @Override
    public List<Credit> findAll() {
        return (List<Credit>)creditRepository.findAll();
    }
}
