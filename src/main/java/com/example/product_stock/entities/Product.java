package com.example.product_stock.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_product")
    private String name;
    private String description;
    private int currentStock;

    private Long supplyerId;
    private Long categoryId;

    public Product() {
    }

    public Product(Long id, String name, String description, int currentStock, Long supplyerId, Long categoryId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.currentStock = currentStock;
        this.supplyerId = supplyerId;
        this.categoryId = categoryId;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCurrentStock() {
        return currentStock;
    }

    public void setCurrentStock(int currentStock) {
        this.currentStock = currentStock;
    }

    public Long getSupplyerId() {
        return supplyerId;
    }

    public void setSupplyerId(Long supplyerId) {
        this.supplyerId = supplyerId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }


}
