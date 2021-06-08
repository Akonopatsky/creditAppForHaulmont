package com.haulmont.creditProccesor.storage.Domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "credits")
public class CreditData {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @Column(name = "creditLimit")
    private BigDecimal creditLimit;

    @Column(name = "interestRate")
    private double interestRate;

    @Column(name = "period")
    private int period;

    @ManyToOne()
    private BankData bank;

    public UUID getId() {
        return id;
    }

    public BigDecimal getCreditLimit() {
        return creditLimit;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public BankData getBank() {
        return bank;
    }

    public int getPeriod() {
        return period;
    }
}
