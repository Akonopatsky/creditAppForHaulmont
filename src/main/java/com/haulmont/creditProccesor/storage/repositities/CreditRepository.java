package com.haulmont.creditProccesor.storage.repositities;


import com.haulmont.creditProccesor.model.Credit;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CreditRepository extends CrudRepository<Credit, UUID> {
}
