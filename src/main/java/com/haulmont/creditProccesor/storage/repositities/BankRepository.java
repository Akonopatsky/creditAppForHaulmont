package com.haulmont.creditProccesor.storage.repositities;

import com.haulmont.creditProccesor.model.Bank;
import com.haulmont.creditProccesor.model.Client;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface BankRepository extends CrudRepository<Bank, UUID> {
}
