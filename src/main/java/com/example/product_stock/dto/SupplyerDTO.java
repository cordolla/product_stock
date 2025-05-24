package com.example.product_stock.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class SupplyerDTO {

    @NotBlank(message = "Nome é obrigatorio")
    private String name;

    @NotBlank(message = "Cnpj é obrigatorio")
    private String cnpj;

    @Email(message = "Email invalido")
    private String email;

    @NotBlank(message = "Telefone é obrigatório")
    private String phone;

    private String address;

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
}
