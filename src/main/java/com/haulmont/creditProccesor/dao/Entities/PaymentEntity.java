package com.haulmont.creditProccesor.dao.Entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "payments")
public class PaymentEntity {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "amountOfPaymant")
    private BigDecimal amountOfPaymant;


    @Column(name = "amountOfBody")
    private BigDecimal amountOfBody;


    @Column(name = "amountOfInterest")
    private BigDecimal amountOfInterest;

    @ManyToOne
    @JoinColumn(name = "creditOffer_id", nullable = false)
    private CreditOfferEntity creditOffer;

    public LocalDate getDate() {
        return date;
    }

    public BigDecimal getAmountOfPaymant() {
        return amountOfPaymant;
    }

    public BigDecimal getAmountOfBody() {
        return amountOfBody;
    }

    public BigDecimal getAmountOfInterest() {
        return amountOfInterest;
    }

    public CreditOfferEntity getCreditOffer() {
        return creditOffer;
    }
}
