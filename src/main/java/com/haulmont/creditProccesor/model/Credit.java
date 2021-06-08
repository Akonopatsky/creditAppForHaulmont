package com.haulmont.creditProccesor.model;

import org.javamoney.moneta.Money;

import javax.persistence.*;
import java.time.Period;
import java.util.UUID;

@Entity
@Table(name = "credits")
public class Credit {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @Column(name = "creditLimit")
    private final Money creditLimit;

    @Column(name = "interestRate")
    private final double interestRate;

    @Column(name = "period")
    private final Period period;

    @ManyToOne()
    private Bank bank;

    public Credit() {
        creditLimit = null;
        this.interestRate = 0d;
        period = null;
    }

    public Credit(Money creditLimit, double interestRate, Period period) {
        this.creditLimit = creditLimit;
        this.interestRate = interestRate;
        this.period = period;
    }

    public UUID getId() {
        return id;
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
