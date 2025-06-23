package com.example.product_stock.dtos;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class SupplierResponseDTO {
    private UUID id;
    private String name;
    private String cnpj;
    private String contactName;
    private String phone;
    private String email;
    private String address;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}