package com.haulmont.creditProccesor.business;

import com.haulmont.creditProccesor.business.model.Credit;
import com.haulmont.creditProccesor.business.model.Payment;
import org.javamoney.moneta.Money;

import java.time.LocalDate;
import java.util.List;

public interface PayStrategy {
    List<Payment> calculate(Credit credit, Money amount, LocalDate date);
}
