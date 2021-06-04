package com.haulmont.creditProccesor.dao.Entities;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "clients")
public class ClientEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "passportNumber")
    private String passportNumber;

    @ManyToMany(mappedBy = "clientSet")
    private Set<BankEntity> bankSet;

    public ClientEntity() {
    }

    public ClientEntity(String name, String phoneNumber, String passportNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.passportNumber = passportNumber;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClientEntity)) return false;
        ClientEntity client = (ClientEntity) o;
        return name.equals(client.name) && Objects.equals(phoneNumber, client.phoneNumber) && passportNumber.equals(client.passportNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, phoneNumber, passportNumber);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", passportNumber='" + passportNumber + '\'' +
                '}';
    }
}
