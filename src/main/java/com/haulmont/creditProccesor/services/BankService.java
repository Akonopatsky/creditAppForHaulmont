package com.haulmont.creditProccesor.services;

import com.haulmont.creditProccesor.Exceptions.CreditProcessorException;
import com.haulmont.creditProccesor.web.dto.BankDto;
import com.haulmont.creditProccesor.web.dto.ClientDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BankService<BankDto> {
    void save(BankDto bankDto);

    BankDto findById(Object id) throws CreditProcessorException;

    List<BankDto> findAll();

    @Transactional
    List<BankDto> findByClient(String client_id) throws CreditProcessorException;
}
