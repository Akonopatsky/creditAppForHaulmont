package com.haulmont.creditProccesor.storage.dao;

import com.haulmont.creditProccesor.Exceptions.CreditProcessorException;
import com.haulmont.creditProccesor.web.dto.BankDto;

import java.util.List;
import java.util.Set;

public interface ClientDao<T,U> {

    void save(T client);

    T findById(Object id) throws CreditProcessorException;

    List<T> findAll();

    Set<T> findByBank(U bank);
}
