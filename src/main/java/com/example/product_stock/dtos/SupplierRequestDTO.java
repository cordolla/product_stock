package com.example.product_stock.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import java.util.UUID;

@Data
public class SupplierRequestDTO {
    private UUID id;

    @NotBlank(message = "Supplier name cannot be blank")
    private String name;

    @NotBlank(message = "CNPJ cannot be blank")
    @Pattern(regexp = "\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}|\\d{14}", message = "Invalid CNPJ format")
    private String cnpj;


    private UUID userId;

    private String contactName;
    private String phone;
    private String email;
    private String address;
}