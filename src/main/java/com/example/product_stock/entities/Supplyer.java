package com.example.product_stock.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "tb_supplyer")
public class Supplyer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_supplyer")
    private String name;

    private String cnpj;
    private String email;
    private String phone;
    private String address;

    public Supplyer() {
    }

    public Supplyer(Long id, String name, String cnpj, String email, String phone, String address) {
        this.id = id;
        this.name = name;
        this.cnpj = cnpj;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Supplyer supplyer = (Supplyer) o;
        return Objects.equals(id, supplyer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
