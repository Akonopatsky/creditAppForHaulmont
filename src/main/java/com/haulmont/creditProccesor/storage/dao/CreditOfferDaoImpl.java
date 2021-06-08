package com.haulmont.creditProccesor.storage.dao;

import com.haulmont.creditProccesor.Exceptions.CreditProcessorException;
import com.haulmont.creditProccesor.storage.Domain.CreditOfferData;
import com.haulmont.creditProccesor.storage.repositities.CreditOfferRepository;

import java.util.List;
import java.util.UUID;

public class CreditOfferDaoImpl implements CreditOfferDao<CreditOfferData> {
    private final CreditOfferRepository creditOfferRepository;

    public CreditOfferDaoImpl(CreditOfferRepository creditOfferRepository) {
        this.creditOfferRepository = creditOfferRepository;
    }

    @Override
    public void save(CreditOfferData creditOffer) {
        creditOfferRepository.save(creditOffer);
    }

    @Override
    public CreditOfferData findById(Object id) throws CreditProcessorException {
        UUID uuid  = UUID.fromString(id.toString());
        return creditOfferRepository.findById(uuid).orElseThrow(
                () -> new CreditProcessorException("there is no creditOffer uuid " + uuid));
    }

    @Override
    public List<CreditOfferData> findAll() {
        return (List<CreditOfferData>)creditOfferRepository.findAll();
    }
}
