package com.haulmont.creditProccesor.storage.dao;

import com.haulmont.creditProccesor.Exceptions.CreditProcessorException;

import java.util.List;

public interface ClientDao<T> {

    void save(T client);

    T findById(Object id) throws CreditProcessorException;

    List<T> findAll();
}
