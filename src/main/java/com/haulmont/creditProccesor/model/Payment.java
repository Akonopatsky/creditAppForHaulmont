package com.haulmont.creditProccesor.model;

import org.javamoney.moneta.Money;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "payments")
public class Payment {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @Column(name = "date")
    private final LocalDate date;

    @Column(name = "amountOfPaymant")
    private final Money amountOfPayment;

    @Column(name = "amountOfBody")
    private final Money amountOfBody;

    @Column(name = "amountOfInterest")
    private final Money amountOfInterest;

    @ManyToOne
    @JoinColumn(name = "creditOffer_id", nullable = false)
    private CreditOffer creditOffer;

    public Payment() {
        date = null;
        amountOfPayment = null;
        amountOfBody = null;
        amountOfInterest = null;
    }

    public Payment(LocalDate date, Money amountOfPayment, Money amountOfBody, Money amountOfInterest) {
        this.date = date;
        this.amountOfPayment = amountOfPayment;
        this.amountOfBody = amountOfBody;
        this.amountOfInterest = amountOfInterest;
    }

    public LocalDate getDate() {
        return date;
    }

    public Money getAmountOfPayment() {
        return amountOfPayment;
    }

    public Money getAmountOfBody() {
        return amountOfBody;
    }

    public Money getAmountOfInterest() {
        return amountOfInterest;
    }
}
