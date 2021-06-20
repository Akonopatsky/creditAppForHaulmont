package com.haulmont.creditProcessor.storage.repositities;



import com.haulmont.creditProcessor.model.Client;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface ClientRepository extends CrudRepository<Client, UUID> {
    Optional<Client> findByPassportNumber(String passport);
}
