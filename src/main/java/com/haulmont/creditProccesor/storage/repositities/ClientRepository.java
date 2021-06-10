package com.haulmont.creditProccesor.storage.repositities;



import com.haulmont.creditProccesor.model.Bank;
import com.haulmont.creditProccesor.model.Client;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClientRepository extends CrudRepository<Client, UUID> {
    Optional<Client> findByPassportNumber(String passport);
}
