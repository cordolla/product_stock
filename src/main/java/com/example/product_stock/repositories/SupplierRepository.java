package com.example.product_stock.repositories;

import com.example.product_stock.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SupplierRepository extends JpaRepository<Supplier, UUID> {
    Optional<Supplier> findById(UUID id);
    Optional<Supplier> findByCpnjOrEmail(String cnpj, String email);

}
