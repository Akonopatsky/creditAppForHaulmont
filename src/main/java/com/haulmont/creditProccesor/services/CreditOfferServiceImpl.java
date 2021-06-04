package com.haulmont.creditProccesor.services;

import com.haulmont.creditProccesor.web.dto.CreditDto;
import com.haulmont.creditProccesor.web.dto.CreditOfferDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class CreditOfferServiceImpl implements CreditOfferService<CreditOfferDto, CreditDto> {

    private static final Logger logger = LoggerFactory.getLogger(CreditOfferServiceImpl.class);

    @Override
    public CreditOfferDto findById(Object id) {
        return null;
    }

    @Override
    public void save(CreditOfferDto creditOffer) {

    }

    @Override
    public List<CreditOfferDto> findAllByCredit(CreditDto credit) {
        return null;
    }
}
