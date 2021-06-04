package com.haulmont.creditProccesor.web.dto;

import java.util.Set;

public class BankDto {
    private String id;

    private String name;

    private Set<String> clientSet;

    private Set<String> creditSet;

    public BankDto() {
    }

    public BankDto(String id, String name, Set<String> clientSet, Set<String> creditSet) {
        this.id = id;
        this.name = name;
        this.clientSet = clientSet;
        this.creditSet = creditSet;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Set<String> getClientSet() {
        return clientSet;
    }

    public void setClientSet(Set<String> clientSet) {
        this.clientSet = clientSet;
    }

    public Set<String> getCreditSet() {
        return creditSet;
    }

    public void setCreditSet(Set<String> creditSet) {
        this.creditSet = creditSet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
