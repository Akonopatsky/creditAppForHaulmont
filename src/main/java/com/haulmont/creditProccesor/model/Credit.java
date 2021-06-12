package com.haulmont.creditProccesor.model;

import com.haulmont.creditProccesor.model.converters.MoneyConverter;
import com.haulmont.creditProccesor.model.converters.PeriodConverter;
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
    @Convert(converter = MoneyConverter.class)
    private Money creditLimit;

    @Column(name = "interestRate")
    private double interestRate;

    @Column(name = "period")
    @Convert(converter = PeriodConverter.class)
    private Period period;

    @ManyToOne()
    private Bank bank;

    public Credit() {
        super();
    }

    public Credit(Money creditLimit, double interestRate, Period period) {
        this.creditLimit = creditLimit;
        this.interestRate = interestRate;
        this.period = period;
    }

    public Credit(Money creditLimit, double interestRate, Period period, Bank bank) {
        this.creditLimit = creditLimit;
        this.interestRate = interestRate;
        this.period = period;
        this.bank = bank;
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

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public Bank getBank() {
        return bank;
    }
}
