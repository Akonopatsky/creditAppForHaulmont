package com.haulmont.creditProccesor.web.dto;

import java.util.Set;

public class BankDto {
    private String uuid;

    private String name;

    private Set<String> clientSet;

    private Set<String> creditSet;

    public BankDto() {
    }

    public BankDto(String uuid, Set<String> clientSet, Set<String> creditSet) {
        this.uuid = uuid;
        this.clientSet = clientSet;
        this.creditSet = creditSet;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
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
