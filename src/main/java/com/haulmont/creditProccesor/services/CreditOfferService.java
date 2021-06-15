package com.haulmont.creditProccesor.services;

import com.haulmont.creditProccesor.Exceptions.CreditProcessorException;
import com.haulmont.creditProccesor.web.dto.ClientDto;
import com.haulmont.creditProccesor.web.dto.CreditOfferDto;

import java.util.List;

public interface CreditOfferService {

    CreditOfferDto findById(Object id) throws CreditProcessorException;

    CreditOfferDto save(CreditOfferDto creditOffer) throws CreditProcessorException;

    List<CreditOfferDto> findByClient(ClientDto client) throws CreditProcessorException;

    void delete(CreditOfferDto offer) throws CreditProcessorException;
}
