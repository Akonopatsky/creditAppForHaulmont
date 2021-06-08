package com.haulmont.creditProccesor.storage;

import com.haulmont.creditProccesor.business.model.Bank;
import com.haulmont.creditProccesor.business.model.Client;
import com.haulmont.creditProccesor.business.model.Credit;
import com.haulmont.creditProccesor.business.model.CreditOffer;
import com.haulmont.creditProccesor.storage.Domain.BankData;
import com.haulmont.creditProccesor.storage.Domain.ClientData;
import com.haulmont.creditProccesor.storage.Domain.CreditData;
import com.haulmont.creditProccesor.storage.Domain.CreditOfferData;
import com.haulmont.creditProccesor.storage.dao.BankDao;
import com.haulmont.creditProccesor.storage.dao.ClientDao;
import com.haulmont.creditProccesor.storage.dao.CreditDao;
import com.haulmont.creditProccesor.storage.dao.CreditOfferDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class StorageServiceImpl implements StorageService {
    private static final Logger logger = LoggerFactory.getLogger(StorageServiceImpl.class);
    private final BankDao<BankData> bankDao;
    private final ClientDao<ClientData> clientDao;
    private final CreditDao<CreditData> creditDao;
    private final CreditOfferDao<CreditOfferData> creditOfferDao;

    public StorageServiceImpl(BankDao<BankData> bankDao, ClientDao<ClientData> clientDao, CreditDao<CreditData> creditDao, CreditOfferDao<CreditOfferData> creditOfferDao) {
        this.bankDao = bankDao;
        this.clientDao = clientDao;
        this.creditDao = creditDao;
        this.creditOfferDao = creditOfferDao;
    }

    @Override
    public void save(Bank bank) {

    }

    @Override
    public void save(Client client) {

    }

    @Override
    public void save(Credit credit) {

    }

    @Override
    public void save(CreditOffer creditOffer) {

    }

    @Override
    public Bank getBank(Object id) {
        return null;
    }

    @Override
    public Client getClient(Object id) {
        return null;
    }

    @Override
    public Credit getCredit(Object id) {
        return null;
    }

    @Override
    public CreditOffer getCreditOffer(Object id) {
        return null;
    }
}
