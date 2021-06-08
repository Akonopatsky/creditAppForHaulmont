package com.haulmont.creditProccesor.model;

import org.javamoney.moneta.Money;

import java.time.LocalDate;
import java.util.List;

public interface PayStrategy {
    List<Payment> calculate(Credit credit, Money amount, LocalDate date);
}
