package com.haulmont.creditProcessor.storage.dao;

import com.haulmont.creditProcessor.Exceptions.CreditProcessorException;

import java.util.List;
import java.util.Optional;

public interface ClientDao<T> {

    void save(T client);

    T findById(Object id) throws CreditProcessorException;

    Optional<T> findByPassportNumber(String passport);

    List<T> findAll();

    void delete(T client);
}
