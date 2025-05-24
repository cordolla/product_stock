package com.example.product_stock.controllers;

import com.example.product_stock.dto.SupplyerDTO;
import com.example.product_stock.entities.Supplyer;
import com.example.product_stock.servicies.SupplyerServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/supplyers")
public class SupplyerController {

    @Autowired
    private SupplyerServices supplyerServices;

    @PostMapping()
    public ResponseEntity<Supplyer> createSupplyer(@Valid @RequestBody SupplyerDTO dto){
        Supplyer savedSupplyer = supplyerServices.save(dto);
        return ResponseEntity.ok(savedSupplyer);
    }

}
