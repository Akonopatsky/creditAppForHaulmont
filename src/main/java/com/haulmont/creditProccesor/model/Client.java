package com.haulmont.creditProccesor.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "clients")
public class Client {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "passportNumber")
    private String passportNumber;

    @ManyToMany(mappedBy = "clientSet")
    private Set<Bank> bankSet = new HashSet<>();

    public Client() {
    }

    public Client(String name, String phoneNumber, String passportNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.passportNumber = passportNumber;
    }

    public Client(UUID id, String name, String phoneNumber, String passportNumber) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.passportNumber = passportNumber;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean addBank(Bank bank) {
        if (bankSet.contains(bank)) {
            return true;
        }
        this.bankSet.add(bank);
        return bank.addClient(this);
    }

    public String getEmail() {
        return email;
    }

    public Set<Bank> getBankSet() {
        return bankSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;
        Client client = (Client) o;
        return id.equals(client.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, phoneNumber, email, passportNumber, bankSet);
    }

    @Override
    public String toString() {
        return "Client{" +
                "uuid=" + id +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", passportNumber='" + passportNumber + '\'' +
                '}';
    }
}
