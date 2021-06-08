package com.haulmont.creditProccesor.storage.repositities;

import com.haulmont.creditProccesor.storage.Domain.CreditData;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CreditRepository extends CrudRepository<CreditData, UUID> {
}
