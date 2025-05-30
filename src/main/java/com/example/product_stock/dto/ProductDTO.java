package com.example.product_stock.dto;

import lombok.Data;

@Data
public class ProductDTO {

    private String name;
    private Integer currentStock;
    private Long supplyerId;
}
