package com.example.product_stock.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class SupplierDTO {
    private UUID id;
    private String name;
}
