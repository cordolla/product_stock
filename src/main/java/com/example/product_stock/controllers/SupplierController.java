package com.example.product_stock.controllers;

import com.example.product_stock.dtos.SupplierRequestDTO;
import com.example.product_stock.dtos.SupplierResponseDTO;
import com.example.product_stock.services.SupplierService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping
    public ResponseEntity<List<SupplierResponseDTO>> getAllSuppliers() {
        List<SupplierResponseDTO> suppliers = supplierService.findAllSuppliersResponseDTO();
        return ResponseEntity.ok(suppliers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupplierResponseDTO> getSupplierById(@PathVariable UUID id) {
        return supplierService.findSupplierResponseDTOById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<SupplierResponseDTO> createSupplier(@Valid @RequestBody SupplierRequestDTO supplierRequestDTO) {
            SupplierResponseDTO createdSupplier = supplierService.createSupplier(supplierRequestDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdSupplier);

    }

    @PutMapping("/{id}")
    public ResponseEntity<SupplierResponseDTO> updateSupplier(@PathVariable UUID id, @Valid @RequestBody SupplierRequestDTO supplierRequestDTO) {
            SupplierResponseDTO updatedSupplier = supplierService.updateSupplier(id, supplierRequestDTO);
            return ResponseEntity.ok(updatedSupplier);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSupplier(@PathVariable UUID id) {
            supplierService.deleteSupplier(id);
            return ResponseEntity.noContent().build();
    }
}