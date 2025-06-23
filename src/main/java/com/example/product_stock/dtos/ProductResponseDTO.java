package com.example.product_stock.dtos;

import com.example.product_stock.entities.Category;
import com.example.product_stock.entities.Supplier;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.w3c.dom.html.HTMLAnchorElement;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDTO {
    private UUID id;
    private String name;
    private String description;
    private String sku;
    private BigDecimal price;
    private Integer currentStock;
    private Integer minStock;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private String supplierName;
    private String categoryName;
}