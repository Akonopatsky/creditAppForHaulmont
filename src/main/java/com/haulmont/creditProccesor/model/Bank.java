package com.haulmont.creditProccesor.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "banks")
public class Bank {
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
    private Set<Credit> creditSet;

    public Bank() {
    }

    public Bank(String name) {
        this.name = name;
        this.clientSet = new HashSet<>();
        this.creditSet = new HashSet<>();
    }

    public boolean addClient(Client client) {
        return clientSet.add(client);
    }

    public boolean addCredit(Credit credit) {
        return creditSet.add(credit);
    }

    public boolean removeClient(Client client) {
        return clientSet.remove(client);
    }

    public boolean removeCredit(Client credit) {
        return creditSet.remove(credit);
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<Client> getClientSet() {
        return clientSet;
    }

    public Set<Credit> getCreditSet() {
        return creditSet;
    }
}
