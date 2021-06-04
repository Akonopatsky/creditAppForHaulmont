package com.haulmont.creditProccesor.dao.Entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "creditOffers")
public class CreditOfferEntity {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private ClientEntity client;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "credit_id")
    private CreditEntity credit;

    @Column(name = "creditAmount")
    private BigDecimal creditAmount;

    @OneToMany(mappedBy = "creditOffer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<PaymentEntity> paymentList;
}
