package com.haulmont.creditProccesor.dao.repositities;

import com.haulmont.creditProccesor.dao.Entities.CreditEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CreditRepository extends CrudRepository<CreditEntity, UUID> {
}
