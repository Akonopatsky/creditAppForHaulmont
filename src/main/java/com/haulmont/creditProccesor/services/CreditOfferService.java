package com.haulmont.creditProccesor.services;

import java.util.List;

public interface CreditOfferService<CreditOfferDto, CreditDto> {

    CreditOfferDto findById(Object id);

    void save(CreditOfferDto creditOffer);

    List<CreditOfferDto> findAllByCredit(CreditDto credit);

}
