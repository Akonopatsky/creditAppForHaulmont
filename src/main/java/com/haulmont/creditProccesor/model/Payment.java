package com.haulmont.creditProccesor.model;

import com.haulmont.creditProccesor.model.converters.MoneyConverter;
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
    private LocalDate date;

    @Column(name = "amountOfPaymant")
    @Convert(converter = MoneyConverter.class)
    private Money amountOfPayment;

    @Column(name = "amountOfBody")
    @Convert(converter = MoneyConverter.class)
    private Money amountOfBody;

    @Column(name = "amountOfInterest")
    @Convert(converter = MoneyConverter.class)
    private Money amountOfInterest;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "offer_id")
    private CreditOffer creditOffer;

    public Payment() {
    }

    public Payment(LocalDate date, Money amountOfPayment, Money amountOfBody, Money amountOfInterest) {
        this.date = date;
        this.amountOfPayment = amountOfPayment;
        this.amountOfBody = amountOfBody;
        this.amountOfInterest = amountOfInterest;
    }

    public UUID getId() {
        return id;
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

    public CreditOffer getCreditOffer() {
        return creditOffer;
    }

    public void setCreditOffer(CreditOffer creditOffer) {
        this.creditOffer = creditOffer;
    }
}
