package com.example.product_stock.controllers;

import com.example.product_stock.dtos.StockMovementRequestDTO;
import com.example.product_stock.dtos.StockMovementResponseDTO;
import com.example.product_stock.services.StockMovementService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stock-movements")
public class StockMovementController {

    private final StockMovementService stockMovementService;


    public StockMovementController(StockMovementService stockMovementService) {
        this.stockMovementService = stockMovementService;
    }

    @PostMapping
    public ResponseEntity<StockMovementResponseDTO> createMovement(@Valid @RequestBody StockMovementRequestDTO requestDTO){
        StockMovementResponseDTO createdMovement = stockMovementService.createMovement(requestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdMovement);
    }
}
