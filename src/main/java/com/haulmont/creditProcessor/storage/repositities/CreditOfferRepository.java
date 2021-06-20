package com.haulmont.creditProcessor.storage.repositities;


import com.haulmont.creditProcessor.model.Client;
import com.haulmont.creditProcessor.model.CreditOffer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface CreditOfferRepository extends CrudRepository<CreditOffer, UUID> {
    List<CreditOffer> findByClient(Client client);
}
