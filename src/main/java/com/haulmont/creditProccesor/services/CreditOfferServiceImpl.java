package com.haulmont.creditProccesor.services;

import com.haulmont.creditProccesor.web.dto.CreditDto;
import com.haulmont.creditProccesor.web.dto.CreditOfferDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditOfferServiceImpl implements CreditOfferService<CreditOfferDto, CreditDto> {

    private static final Logger logger = LoggerFactory.getLogger(CreditOfferServiceImpl.class);

    @Override
    public CreditOfferDto findById(Object id) {
        logger.info("find credit offer bi id {}", id);
        return null;
    }

    @Override
    public void save(CreditOfferDto creditOffer) {
        logger.info("save credit offer {}", creditOffer);
    }

    @Override
    public List<CreditOfferDto> findAllByCredit(CreditDto credit) {
        logger.info("find all offers by credit {}", credit);
        return null;
    }
}
