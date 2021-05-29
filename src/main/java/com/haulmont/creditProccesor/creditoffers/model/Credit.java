package com.haulmont.creditProccesor.creditoffers.model;

import com.haulmont.creditProccesor.creditoffers.money.MonetaryAmount;

import java.time.Period;
import java.util.UUID;

public class Credit {

    private UUID uuid;

    private MonetaryAmount creditLimit;

    private double interestRate;

    private Period period;

    public Credit(MonetaryAmount creditLimit, double interestRate, Period period) {
        this.creditLimit = creditLimit;
        this.interestRate = interestRate;
        this.period = period;
    }

    public UUID getUuid() {
        return uuid;
    }

    public MonetaryAmount getCreditLimit() {
        return creditLimit;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public Period getPeriod() {
        return period;
    }

}
