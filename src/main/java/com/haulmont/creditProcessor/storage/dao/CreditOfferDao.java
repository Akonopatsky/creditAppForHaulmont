package com.haulmont.creditProcessor.storage.dao;

import com.haulmont.creditProcessor.Exceptions.CreditProcessorException;
import com.haulmont.creditProcessor.model.Client;
import com.haulmont.creditProcessor.model.CreditOffer;

import java.util.List;
public interface CreditOfferDao <T>{

    void save(T creditOffer);

    T findById(Object id) throws CreditProcessorException;

    List<T> findAll();


    List<CreditOffer> findByClient(Client client);

    void delete(T offer);
}
