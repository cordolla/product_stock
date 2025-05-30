package com.example.product_stock.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tb_supplyer")
public class SupplyerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_supplyer")
    private String name;

    @Email
    private String email;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
