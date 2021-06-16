package com.haulmont.creditProccesor.storage.dao;

import com.haulmont.creditProccesor.Exceptions.CreditProcessorException;
import com.haulmont.creditProccesor.model.Client;

import java.util.List;
import java.util.Set;

public interface BankDao<T> {

    void save(T bank);

    T findById(Object id) throws CreditProcessorException;

    List<T> findAll();

    void delete(T bank);
}
