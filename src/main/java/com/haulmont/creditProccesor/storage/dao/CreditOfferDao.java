package com.haulmont.creditProccesor.storage.dao;

import com.haulmont.creditProccesor.Exceptions.CreditProcessorException;

import java.util.List;
public interface CreditOfferDao <T>{

    void save(T creditOffer);

    T findById(Object id) throws CreditProcessorException;

    List<T> findAll();
}
