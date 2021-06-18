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

    @Column(name = "passportNumber")
    private String passportNumber;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @ManyToMany(mappedBy = "clientSet")
    private final Set<Bank> bankSet = new HashSet<>();

    public Client() {
    }

    public Client(String name, String passportNumber, String phoneNumber, String email) {
        this.name = name;
        this.passportNumber = passportNumber;
        this.phoneNumber = phoneNumber;
        this.email = email;
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
        bankSet.add(bank);
        return bank.addClient(this);
    }

    public boolean removeBank(Bank bank) {
        if (!bankSet.contains(bank)) {
            return true;
        }
        bankSet.remove(bank);
        return bank.removeClient(this);
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
