package com.example.product_stock.dtos;

import com.example.product_stock.entities.StockMovement;
import com.example.product_stock.entities.Supplier;
import lombok.Data;

import java.util.List;

@Data
public class UserResponseDTO {
    private String username;
    private String email;
    private List<StockMovement> stockMovementList;
    private List<Supplier> suppliers;
}
