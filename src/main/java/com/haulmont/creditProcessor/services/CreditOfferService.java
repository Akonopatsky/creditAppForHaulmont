package com.haulmont.creditProcessor.services;

import com.haulmont.creditProcessor.Exceptions.CreditProcessorException;
import com.haulmont.creditProcessor.web.dto.ClientDto;
import com.haulmont.creditProcessor.web.dto.CreditOfferDto;

import java.util.List;

public interface CreditOfferService {

    CreditOfferDto findById(Object id) throws CreditProcessorException;

    CreditOfferDto save(CreditOfferDto creditOffer) throws CreditProcessorException;

    List<CreditOfferDto> findByClient(ClientDto client) throws CreditProcessorException;

    void delete(CreditOfferDto offer) throws CreditProcessorException;
}
