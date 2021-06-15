package com.haulmont.creditProccesor.storage.repositities;


import com.haulmont.creditProccesor.model.Client;
import com.haulmont.creditProccesor.model.CreditOffer;
import com.haulmont.creditProccesor.web.dto.CreditOfferDto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface CreditOfferRepository extends CrudRepository<CreditOffer, UUID> {
    List<CreditOffer> findByClient(Client client);
}
