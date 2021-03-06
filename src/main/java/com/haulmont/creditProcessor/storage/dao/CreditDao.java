package com.haulmont.creditProcessor.storage.dao;

import com.haulmont.creditProcessor.Exceptions.CreditProcessorException;

import java.util.List;

public interface CreditDao<T> {
    void save(T credit);

    T findById(Object id) throws CreditProcessorException;

    List<T> findAll();

    void delete(T credit);
}
