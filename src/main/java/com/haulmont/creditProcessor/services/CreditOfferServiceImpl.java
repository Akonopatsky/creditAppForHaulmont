package com.haulmont.creditProcessor.services;

import com.haulmont.creditProcessor.Exceptions.CreditProcessorException;
import com.haulmont.creditProcessor.model.*;
import com.haulmont.creditProcessor.services.mappers.CreditOfferMapper;
import com.haulmont.creditProcessor.storage.dao.ClientDao;
import com.haulmont.creditProcessor.storage.dao.CreditDao;
import com.haulmont.creditProcessor.storage.dao.CreditOfferDao;
import com.haulmont.creditProcessor.web.dto.ClientDto;
import com.haulmont.creditProcessor.web.dto.CreditOfferDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CreditOfferServiceImpl implements CreditOfferService {
    private static final Logger logger = LoggerFactory.getLogger(CreditOfferServiceImpl.class);

    private final CreditOfferDao<CreditOffer> creditOfferDao;
    private final CreditDao<Credit> creditDao;
    private final ClientDao<Client> clientDao;
    private final CreditOfferMapper creditOfferMapper;
    private final PayStrategyRegistry<String, PayStrategy> payStrategyRegistry;
    private final MoneyFactory moneyFactory;

    public CreditOfferServiceImpl(CreditOfferDao<CreditOffer> creditOfferDao, CreditDao<Credit> creditDao, ClientDao<Client> clientDao, CreditOfferMapper creditOfferMapper, PayStrategyRegistry<String, PayStrategy> payStrategyRegistry, MoneyFactory moneyFactory) {
        this.creditOfferDao = creditOfferDao;
        this.creditDao = creditDao;
        this.clientDao = clientDao;
        this.creditOfferMapper = creditOfferMapper;
        this.payStrategyRegistry = payStrategyRegistry;
        this.moneyFactory = moneyFactory;
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
                .creditAmount(moneyFactory.getMoneyOf(creditOfferDto.getCreditAmount()))
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
