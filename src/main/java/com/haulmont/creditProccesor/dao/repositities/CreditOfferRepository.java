package com.haulmont.creditProccesor.dao.repositities;


import com.haulmont.creditProccesor.dao.Entities.CreditOffer;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CreditOfferRepository extends CrudRepository<CreditOffer, UUID> {
}
