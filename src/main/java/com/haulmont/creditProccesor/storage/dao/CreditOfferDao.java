package com.haulmont.creditProccesor.storage.dao;

import com.haulmont.creditProccesor.Exceptions.CreditProcessorException;
import com.haulmont.creditProccesor.model.Client;
import com.haulmont.creditProccesor.model.CreditOffer;

import java.util.List;
public interface CreditOfferDao <T>{

    void save(T creditOffer);

    T findById(Object id) throws CreditProcessorException;

    List<T> findAll();


    List<CreditOffer> findByClient(Client client);

    void delete(T offer);
}
