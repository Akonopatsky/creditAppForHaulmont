package com.haulmont.creditProccesor.services;

import com.haulmont.creditProccesor.Exceptions.CreditProcessorException;
import com.haulmont.creditProccesor.web.dto.CreditDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CreditService {

    @Transactional
    void createCredit(com.haulmont.creditProccesor.web.dto.CreditDto creditDto) throws CreditProcessorException;

    CreditDto findById(String id) throws CreditProcessorException;

    List<com.haulmont.creditProccesor.web.dto.CreditDto> findByBank(String id) throws CreditProcessorException;

    void save(CreditDto creditDto) throws CreditProcessorException;

    void delete(CreditDto creditDto) throws CreditProcessorException;
}
