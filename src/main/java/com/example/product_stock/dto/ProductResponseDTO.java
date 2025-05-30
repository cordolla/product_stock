package com.example.product_stock.dto;

import com.example.product_stock.entities.SupplyerEntity;

import java.util.UUID;

public record ProductResponseDTO(UUID id, String name, Integer currentStock) {
}
