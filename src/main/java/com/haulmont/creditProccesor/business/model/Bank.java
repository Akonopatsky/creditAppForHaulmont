package com.haulmont.creditProccesor.business.model;

import java.util.Set;
import java.util.UUID;

public class Bank<T, S> {

    private UUID uuid;

    private String name;

    private Set<T> clientSet;

    private Set<S> creditSet;

    public <U extends T> boolean addClients(U client) {
        return clientSet.add(client);
    }

    public <U extends S> boolean addCredit(U credit) {
        return creditSet.add(credit);
    }

    public <U extends T> boolean removeClient(U client) {
        return clientSet.remove(client);
    }

    public <U extends S> boolean removeCredit(U credit) {
        return creditSet.remove(credit);
    }

}
