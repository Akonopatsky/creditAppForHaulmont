package com.haulmont.creditProccesor.dao.repositities;

import com.haulmont.creditProccesor.dao.Entities.ClientEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ClientRepository extends CrudRepository<ClientEntity, UUID> {
}
