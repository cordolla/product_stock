package com.example.product_stock.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "tb_product")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;
    private Integer currentStock;

    @ManyToOne
    @JoinColumn(name = "supplyer_id", insertable = false, updatable = false)
    private SupplyerEntity supplyerEntity;

    @CreationTimestamp
    private LocalDateTime createdAt;

}
