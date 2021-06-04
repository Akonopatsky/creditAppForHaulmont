package com.haulmont.creditProccesor.dao;

import com.haulmont.creditProccesor.Exceptions.CreditProcessorException;
import com.haulmont.creditProccesor.dao.Entities.BankEntity;

import java.util.Set;

public interface BankDao<T> {

    void save(T bank);

    BankEntity findById(Object id) throws CreditProcessorException;

    Set<BankEntity> findAll();
}
