package com.example.product_stock.dto;

import com.example.product_stock.entities.SupplyerEntity;

public record CreateProductRequestDTO(String name, Integer currentStock, SupplyerEntity supplyerEntity) {
}
