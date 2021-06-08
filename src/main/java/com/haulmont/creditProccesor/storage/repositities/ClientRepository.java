package com.haulmont.creditProccesor.storage.repositities;

import com.haulmont.creditProccesor.storage.Domain.ClientData;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ClientRepository extends CrudRepository<ClientData, UUID> {
}
