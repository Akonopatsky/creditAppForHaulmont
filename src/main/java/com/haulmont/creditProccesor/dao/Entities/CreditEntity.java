package com.haulmont.creditProccesor.dao.Entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "credits")
public class CreditEntity {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @Column(name = "creditLimit")
    private BigDecimal creditLimit;

    @Column(name = "interestRate")
    private double interestRate;

    @ManyToOne()
    private BankEntity bank;
}
