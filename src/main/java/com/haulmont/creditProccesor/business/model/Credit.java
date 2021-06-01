package com.haulmont.creditProccesor.business.model;

import org.javamoney.moneta.Money;

import java.time.Period;
import java.util.UUID;

public class Credit {

    private UUID uuid;

    private final Money creditLimit;

    private final double interestRate;

    private final Period period;

    public Credit(Money creditLimit, double interestRate, Period period) {
        this.creditLimit = creditLimit;
        this.interestRate = interestRate;
        this.period = period;
    }

    public UUID getUuid() {
        return uuid;
    }

    public Money getCreditLimit() {
        return creditLimit;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public Period getPeriod() {
        return period;
    }

}
