package com.haulmont.creditProccesor.creditoffers.model;

import jdk.jfr.Percentage;
import org.javamoney.moneta.Money;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

public interface PayStrategy {
    List<Payment> calculate(Credit credit, Money amount, LocalDate date);
}
