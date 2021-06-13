package com.haulmont.creditProccesor.web.dto;

import java.util.Set;

public class BankDto {
    private String id;

    private String name;

    public BankDto() {
    }

    public BankDto(String id, String name, Set<String> clientSet, Set<String> creditSet) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
