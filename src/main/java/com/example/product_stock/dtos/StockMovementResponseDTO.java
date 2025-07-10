package com.example.product_stock.dtos;

import com.example.product_stock.enums.MovementType;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class StockMovementResponseDTO {

    private Long id;
    private Integer quantity;
    private MovementType type;
    private String description;
    private LocalDateTime movementDate;

    private UUID productId;
    private String productName;
    private UUID userId;
    private String userName;

    private Integer stockBeforeMovement;
    private Integer stockAfterMovement;
}
