package com.example.product_stock.dtos;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class ProductRequestDTO {
    @NotBlank(message = "Product name cannot be blank")
    private String name;

    private String description;

    private String sku;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal price;

    @Min(value = 0 , message = "Current stock cannot be negative")
    private Integer currentStock = 0;

    @Min(value = 0, message = "Minimum stock cannot be negative")
    private Integer minStock = 0;

    @NotNull(message = "Supplier ID cannot be null")
    private UUID supplierId;

    @NotNull(message = "Category ID cannot be null")
    private UUID categoryId;

}
