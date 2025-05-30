package com.example.product_stock.services;

import com.example.product_stock.dto.CreateProductRequestDTO;
import com.example.product_stock.dto.ProductResponseDTO;
import com.example.product_stock.entities.ProductEntity;
import com.example.product_stock.exceptions.ProductFoundException;
import com.example.product_stock.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateProductService {

    @Autowired
    private ProductRepository productRepository;

    public ProductResponseDTO execute(CreateProductRequestDTO createProductRequestDTO) {
        productRepository.findByName(createProductRequestDTO.name())
                .ifPresent((product) -> {
                    throw new ProductFoundException();
                });
        ProductEntity productEntity = new ProductEntity();
        productEntity.setName(createProductRequestDTO.name());
        productEntity.setCurrentStock(createProductRequestDTO.currentStock());

        ProductEntity savedProduct = productRepository.save(productEntity);

        return new ProductResponseDTO(
                savedProduct.getId(),
                savedProduct.getName(),
                savedProduct.getCurrentStock()
        );
    }
}
