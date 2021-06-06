package com.haulmont.creditProccesor.dao.Entities;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "banks")
public class BankEntity {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToMany
    @JoinTable(
            name = "bank_client",
            joinColumns = @JoinColumn(name = "bank_id"),
            inverseJoinColumns = @JoinColumn(name = "client_id"))
    private Set<Client> clientSet;

    @OneToMany(mappedBy = "bank", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<CreditEntity> creditSet;

    public BankEntity() {
    }

    public BankEntity(String name) {
        this.name = name;
        this.clientSet = new HashSet<>();
        this.creditSet = new HashSet<>();
    }

    public UUID getId() {
        return id;
    }

    public Set<Client> getClientSet() {
        return clientSet;
    }

    public Set<CreditEntity> getCreditList() {
        return creditSet;
    }

    public String getName() {
        return name;
    }
}
