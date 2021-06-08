package com.haulmont.creditProccesor.storage.repositities;


import com.haulmont.creditProccesor.storage.Domain.CreditOfferData;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CreditOfferRepository extends CrudRepository<CreditOfferData, UUID> {
}
