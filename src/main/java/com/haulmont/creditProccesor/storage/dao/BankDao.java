package com.haulmont.creditProccesor.storage.dao;

import com.haulmont.creditProccesor.Exceptions.CreditProcessorException;

import java.util.Set;

public interface BankDao<T> {

    void save(T bank);

    T findById(Object id) throws CreditProcessorException;

    Set<T> findAll();
}
