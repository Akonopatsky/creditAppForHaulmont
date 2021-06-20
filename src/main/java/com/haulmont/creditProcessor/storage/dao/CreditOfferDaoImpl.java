package com.haulmont.creditProcessor.storage.dao;

import com.haulmont.creditProcessor.Exceptions.CreditProcessorException;
import com.haulmont.creditProcessor.model.Client;
import com.haulmont.creditProcessor.model.CreditOffer;
import com.haulmont.creditProcessor.storage.repositities.CreditOfferRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class CreditOfferDaoImpl implements CreditOfferDao<CreditOffer> {
    private final CreditOfferRepository creditOfferRepository;

    public CreditOfferDaoImpl(CreditOfferRepository creditOfferRepository) {
        this.creditOfferRepository = creditOfferRepository;
    }

    @Override
    public void save(CreditOffer creditOffer) {
        creditOfferRepository.save(creditOffer);
    }

    @Override
    public CreditOffer findById(Object id) throws CreditProcessorException {
        UUID uuid = UUID.fromString(id.toString());
        return creditOfferRepository.findById(uuid).orElseThrow(
                () -> new CreditProcessorException("there is no creditOffer uuid " + uuid));
    }

    @Override
    public List<CreditOffer> findAll() {
        return (List<CreditOffer>) creditOfferRepository.findAll();
    }

    @Override
    public List<CreditOffer> findByClient(Client client) {
        return creditOfferRepository.findByClient(client);
    }

    @Override
    public void delete(CreditOffer offer) {
        creditOfferRepository.delete(offer);
    }
}
