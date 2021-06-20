package com.haulmont.creditProcessor.storage.repositities;


import com.haulmont.creditProcessor.model.Credit;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CreditRepository extends CrudRepository<Credit, UUID> {
}
