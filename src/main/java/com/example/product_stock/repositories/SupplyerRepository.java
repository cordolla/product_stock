package com.example.product_stock.repositories;

import com.example.product_stock.entities.SupplyerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplyerRepository extends JpaRepository<SupplyerEntity, Long> {
}
