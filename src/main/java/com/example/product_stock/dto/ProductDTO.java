package com.example.product_stock.dto;

import com.example.product_stock.entities.Product;

public class ProductDTO {

    private String name;
    private String description;
    private int currentStock;
    private Long supplyerId;
    private Long categoryId;

    public ProductDTO() {
    }

    public ProductDTO(Product product) {
        this.name = product.getName();
        this.description = product.getDescription();
        this.currentStock = product.getCurrentStock();
        this.supplyerId = product.getSupplyerId();
        this.categoryId = product.getCategoryId();
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
