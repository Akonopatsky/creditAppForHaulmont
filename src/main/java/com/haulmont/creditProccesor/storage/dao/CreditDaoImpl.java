package com.haulmont.creditProccesor.storage.dao;

import com.haulmont.creditProccesor.Exceptions.CreditProcessorException;
import com.haulmont.creditProccesor.storage.Domain.CreditData;
import com.haulmont.creditProccesor.storage.repositities.CreditRepository;

import java.util.List;
import java.util.UUID;

public class CreditDaoImpl implements CreditDao<CreditData> {
    private final CreditRepository creditRepository;

    public CreditDaoImpl(CreditRepository creditRepository) {
        this.creditRepository = creditRepository;
    }

    @Override
    public void save(CreditData credit) {
        creditRepository.save(credit);
    }

    @Override
    public CreditData findById(Object id) throws CreditProcessorException {
        UUID uuid  = UUID.fromString(id.toString());
        return creditRepository.findById(uuid).orElseThrow(
                () -> new CreditProcessorException("there is no credit uuid " + uuid));
    }

    @Override
    public List<CreditData> findAll() {
        return (List<CreditData>)creditRepository.findAll();
    }
}
