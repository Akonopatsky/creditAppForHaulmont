package com.haulmont.creditProccesor.services;

import com.haulmont.creditProccesor.Exceptions.CreditProcessorException;
import com.haulmont.creditProccesor.model.*;
import com.haulmont.creditProccesor.services.mappers.CreditOfferMapper;
import com.haulmont.creditProccesor.storage.dao.ClientDao;
import com.haulmont.creditProccesor.storage.dao.CreditDao;
import com.haulmont.creditProccesor.storage.dao.CreditOfferDao;
import com.haulmont.creditProccesor.web.dto.ClientDto;
import com.haulmont.creditProccesor.web.dto.CreditDto;
import com.haulmont.creditProccesor.web.dto.CreditOfferDto;
import org.javamoney.moneta.Money;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;

@Service
public class CreditOfferServiceImpl implements CreditOfferService {
    private static final Logger logger = LoggerFactory.getLogger(CreditOfferServiceImpl.class);

    private final CreditOfferDao<CreditOffer> creditOfferDao;
    private final CreditDao<Credit> creditDao;
    private final ClientDao<Client> clientDao;
    private final CreditOfferMapper creditOfferMapper;
    private final PayStrategyRegistry<String, PayStrategy> payStrategyRegistry;

    public CreditOfferServiceImpl(CreditOfferDao<CreditOffer> creditOfferDao, CreditDao<Credit> creditDao, ClientDao<Client> clientDao, CreditOfferMapper creditOfferMapper, PayStrategyRegistry<String, PayStrategy> payStrategyRegistry) {
        this.creditOfferDao = creditOfferDao;
        this.creditDao = creditDao;
        this.clientDao = clientDao;
        this.creditOfferMapper = creditOfferMapper;
        this.payStrategyRegistry = payStrategyRegistry;
    }

    @Override
    public CreditOfferDto findById(Object id) throws CreditProcessorException {
        logger.info("find credit offer bi id {}", id);
        return creditOfferMapper.convertToDto(creditOfferDao.findById(id));
    }

    @Override
    @Transactional
    public CreditOfferDto save(CreditOfferDto creditOfferDto) throws CreditProcessorException {
        logger.info("save credit offer {}", creditOfferDto);
        Client client = clientDao.findById(creditOfferDto.getClientId());
        Credit credit = creditDao.findById(creditOfferDto.getCreditId());
        CreditOffer creditOffer = new CreditOffer.OfferBuilder()
                .payStrategy(payStrategyRegistry.get(creditOfferDto.getPayStrategy().toUpperCase()))
                .creditAmount(Money.of(creditOfferDto.getCreditAmount(), "RUB"))
                .beginDate(creditOfferDto.getBeginDate())
                .credit(credit)
                .client(client)
                .build();
        creditOfferDao.save(creditOffer);
        return creditOfferMapper.convertToDto(creditOffer);
    }

    @Override
    public List<CreditOfferDto> findByClient(ClientDto clientDto) throws CreditProcessorException {
        logger.info("find all offers by client {}", clientDto);
        return creditOfferMapper.convertToDtoList(creditOfferDao.findByClient(clientDao.findById(clientDto.getId())));
    }

    @Override
    public void delete(CreditOfferDto offer) throws CreditProcessorException {
        creditOfferDao.delete(creditOfferDao.findById(offer.getId()));
    }
}
