package com.haulmont.creditProcessor.storage.repositities;

import com.haulmont.creditProcessor.model.Bank;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface BankRepository extends CrudRepository<Bank, UUID> {
}
