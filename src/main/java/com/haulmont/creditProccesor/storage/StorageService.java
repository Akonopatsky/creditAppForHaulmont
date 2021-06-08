package com.haulmont.creditProccesor.storage;

import com.haulmont.creditProccesor.business.model.Bank;
import com.haulmont.creditProccesor.business.model.Client;
import com.haulmont.creditProccesor.business.model.Credit;
import com.haulmont.creditProccesor.business.model.CreditOffer;

import java.util.UUID;

public interface StorageService {
    void save(Bank bank);

    void save(Client client);

    void save(Credit credit);

    void save(CreditOffer creditOffer);

    Bank getBank(Object id);

    Client getClient(Object id);

    Credit getCredit(Object id);

    CreditOffer getCreditOffer(Object id);

}
