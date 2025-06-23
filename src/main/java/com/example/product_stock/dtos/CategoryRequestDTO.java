package com.example.product_stock.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.util.UUID;

@Data
public class CategoryRequestDTO {
    private UUID id;

    @NotBlank(message = "Category name cannot be blank")
    private String name;

    private String description;
}