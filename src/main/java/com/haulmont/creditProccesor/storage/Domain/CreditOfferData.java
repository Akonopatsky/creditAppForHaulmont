package com.haulmont.creditProccesor.storage.Domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "creditOffers")
public class CreditOfferData {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private ClientData client;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "credit_id")
    private CreditData credit;

    @Column(name = "creditAmount")
    private BigDecimal creditAmount;

    @OneToMany(mappedBy = "creditOffer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<PaymentData> paymentList;

    public UUID getId() {
        return id;
    }

    public ClientData getClient() {
        return client;
    }

    public CreditData getCredit() {
        return credit;
    }

    public BigDecimal getCreditAmount() {
        return creditAmount;
    }

    public List<PaymentData> getPaymentList() {
        return paymentList;
    }
}
