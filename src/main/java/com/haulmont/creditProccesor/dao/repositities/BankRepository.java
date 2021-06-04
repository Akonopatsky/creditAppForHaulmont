package com.haulmont.creditProccesor.dao.repositities;

import com.haulmont.creditProccesor.dao.Entities.BankEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface BankRepository extends CrudRepository<BankEntity, UUID> {
}
