package com.haulmont.creditProccesor.storage.dao;

import com.haulmont.creditProccesor.Exceptions.CreditProcessorException;
import com.haulmont.creditProccesor.model.Client;
import com.haulmont.creditProccesor.web.dto.BankDto;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ClientDao<T> {

    void save(T client);

    T findById(Object id) throws CreditProcessorException;

    Optional<T> findByPassportNumber(String passport);

    List<T> findAll();

    void delete(T client);
}
