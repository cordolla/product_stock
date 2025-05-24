package com.example.product_stock.controllers;

import com.example.product_stock.dto.ProductDTO;
import com.example.product_stock.entities.Product;
import com.example.product_stock.servicies.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    ProductServices productServices;

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody ProductDTO dto){
        Product savedProduct = productServices.save(dto);
        return ResponseEntity.ok(savedProduct);
    }

    @GetMapping
    public List<ProductDTO> findAll(){
        return productServices.findAll();
    }
}
