package com.haulmont.creditProccesor.services;

import java.util.List;

public interface BankService<BankDto> {
    void save(BankDto bankDto);

    BankDto getById(Object id);

    List<BankDto> getAll();
}
