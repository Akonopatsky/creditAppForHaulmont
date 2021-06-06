package com.haulmont.creditProccesor.dao.Entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "creditOffers")
public class CreditOffer {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "credit_id")
    private CreditEntity credit;

    @Column(name = "creditAmount")
    private BigDecimal creditAmount;

    @OneToMany(mappedBy = "creditOffer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Payment> paymentList;

    public UUID getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public CreditEntity getCredit() {
        return credit;
    }

    public BigDecimal getCreditAmount() {
        return creditAmount;
    }

    public List<Payment> getPaymentList() {
        return paymentList;
    }
}
