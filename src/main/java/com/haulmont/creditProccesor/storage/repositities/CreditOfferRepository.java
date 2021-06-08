package com.haulmont.creditProccesor.storage.repositities;


import com.haulmont.creditProccesor.model.CreditOffer;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CreditOfferRepository extends CrudRepository<CreditOffer, UUID> {
}
