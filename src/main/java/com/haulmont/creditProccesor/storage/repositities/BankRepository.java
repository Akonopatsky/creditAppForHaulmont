package com.haulmont.creditProccesor.storage.repositities;

import com.haulmont.creditProccesor.storage.Domain.BankData;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface BankRepository extends CrudRepository<BankData, UUID> {
}
