package com.example.product_stock.services;

import com.example.product_stock.dtos.StockMovementRequestDTO;
import com.example.product_stock.dtos.StockMovementResponseDTO;
import com.example.product_stock.entities.Product;
import com.example.product_stock.entities.StockMovement;
import com.example.product_stock.entities.User;
import com.example.product_stock.enums.MovementType;
import com.example.product_stock.repositories.ProductRepository;
import com.example.product_stock.repositories.StockMovementRepository;
import com.example.product_stock.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StockMovementService {

    private final StockMovementRepository stockMovementRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public StockMovementService(StockMovementRepository stockMovementRepository, ProductRepository productRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.stockMovementRepository = stockMovementRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public StockMovementResponseDTO createMovement(StockMovementRequestDTO requestDTO) {
        Product product = productRepository.findById(requestDTO.getProductId())
                .orElseThrow(() -> new IllegalArgumentException("Product not found with ID: " + requestDTO.getProductId()));
        User user = userRepository.findById(requestDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException(("User not found with ID: " + requestDTO.getUserId())));

        Integer stockBefore = product.getCurrentStock();
        Integer stockAfter;

        if (requestDTO.getType() == MovementType.OUTPUT){
            if (stockBefore< requestDTO.getQuantity()) {
                throw new IllegalArgumentException("Insufficient stock for the product: " + product.getName());
            }
            stockAfter = stockBefore - requestDTO.getQuantity();
        } else {
            stockAfter = stockBefore + requestDTO.getQuantity();
        }

        product.setCurrentStock(stockAfter);
        productRepository.save(product);

        StockMovement movement = new StockMovement();
        movement.setProduct(product);
        movement.setUser(user);
        movement.setType(requestDTO.getType());
        movement.setQuantity(requestDTO.getQuantity());
        movement.setDescription(requestDTO.getDescription());
        movement.setStockBeforeMovement(stockBefore);
        movement.setStockAfterMovement(stockAfter);

        StockMovement savedMovement = stockMovementRepository.save(movement);

        StockMovementResponseDTO responseDTO = modelMapper.map(savedMovement, StockMovementResponseDTO.class);
        responseDTO.setProductName(product.getName());
        responseDTO.setUserName(user.getUsername());

        return responseDTO;
    }
}
