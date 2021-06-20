package com.haulmont.creditProcessor.services;

import com.haulmont.creditProcessor.Exceptions.CreditProcessorException;
import com.haulmont.creditProcessor.web.dto.CreditDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CreditService {

    @Transactional
    void createCredit(CreditDto creditDto) throws CreditProcessorException;

    CreditDto findById(String id) throws CreditProcessorException;

    List<CreditDto> findByBank(String id) throws CreditProcessorException;

    void save(CreditDto creditDto) throws CreditProcessorException;

    void delete(CreditDto creditDto) throws CreditProcessorException;
}
