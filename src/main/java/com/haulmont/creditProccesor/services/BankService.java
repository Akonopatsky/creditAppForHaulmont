package com.haulmont.creditProccesor.services;

public interface BankService<T> {
    void save(T bankDto);
    T getById(Object id);
}
