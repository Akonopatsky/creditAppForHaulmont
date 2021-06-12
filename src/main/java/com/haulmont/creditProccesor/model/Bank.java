package com.haulmont.creditProccesor.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
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

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "bank_client",
            joinColumns = @JoinColumn(name = "bank_id"),
            inverseJoinColumns = @JoinColumn(name = "client_id"))
    private Set<Client> clientSet = new HashSet<>();

    @OneToMany(mappedBy = "bank", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Credit> creditSet = new HashSet<>();


    public Bank() {
    }

    public Bank(String name) {
        this.name = name;
    }

    public boolean addClient(Client client) {
        clientSet.add(client);
        return client.addBank(this);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bank)) return false;
        Bank bank = (Bank) o;
        return id.equals(bank.id) && name.equals(bank.name) && Objects.equals(clientSet, bank.clientSet) && Objects.equals(creditSet, bank.creditSet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
