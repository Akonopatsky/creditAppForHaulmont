package com.haulmont.creditProcessor.storage.repositities;

import com.haulmont.creditProcessor.model.Payment;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface PaymentRepository extends CrudRepository<Payment, UUID> {
}