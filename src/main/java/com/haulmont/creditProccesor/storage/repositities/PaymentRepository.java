package com.haulmont.creditProccesor.storage.repositities;

import com.haulmont.creditProccesor.model.Payment;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface PaymentRepository extends CrudRepository<Payment, UUID> {
}