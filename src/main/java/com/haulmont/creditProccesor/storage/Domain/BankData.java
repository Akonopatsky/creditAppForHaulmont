package com.haulmont.creditProccesor.storage.Domain;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "banks")
public class BankData {
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
    private Set<ClientData> clientSet;

    @OneToMany(mappedBy = "bank", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<CreditData> creditSet;

    public BankData() {
    }

    public BankData(String name) {
        this.name = name;
        this.clientSet = new HashSet<>();
        this.creditSet = new HashSet<>();
    }

    public UUID getId() {
        return id;
    }

    public Set<ClientData> getClientSet() {
        return clientSet;
    }

    public Set<CreditData> getCreditList() {
        return creditSet;
    }

    public String getName() {
        return name;
    }
}
