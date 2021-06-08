package com.haulmont.creditProccesor.business.model;

import java.util.Set;
import java.util.UUID;

public class Bank<Client, Credit> {

    private UUID id;

    private String name;

    private Set<Client> clientSet;

    private Set<Credit> creditSet;

    public Bank(String name) {
        this.name = name;
    }

    public <U extends Client> boolean addClients(U client) {
        return clientSet.add(client);
    }

    public <U extends Credit> boolean addCredit(U credit) {
        return creditSet.add(credit);
    }

    public <U extends Client> boolean removeClient(U client) {
        return clientSet.remove(client);
    }

    public <U extends Credit> boolean removeCredit(U credit) {
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
