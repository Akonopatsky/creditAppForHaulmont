package com.haulmont.creditProcessor.storage.dao;

import com.haulmont.creditProcessor.Exceptions.CreditProcessorException;

import com.haulmont.creditProcessor.model.Credit;
import com.haulmont.creditProcessor.storage.repositities.CreditRepository;
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

    @Override
    public void delete(Credit credit) {
        creditRepository.delete(credit);
    }
}
