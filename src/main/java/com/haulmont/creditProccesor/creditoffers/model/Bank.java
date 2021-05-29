package com.haulmont.creditProccesor.creditoffers.model;

import java.util.Set;
import java.util.UUID;

public class Bank<T, S> {

    private UUID uuid;

    private Set<T> clientSet;

    private Set<S> creditSet;

    public void addClients(T client) {
        clientSet.add(client);
    }

    public void addCredit(S credit) {
        creditSet.add(credit);
    }
}
