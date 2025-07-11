package com.example.product_stock.controllers;

import com.example.product_stock.dtos.ProductResponseDTO;
import com.example.product_stock.dtos.UserRequestDTO;
import com.example.product_stock.dtos.UserResponseDTO;
import com.example.product_stock.services.ProductService;
import com.example.product_stock.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final ProductService productService;

    public UserController(UserService userService, ProductService productService) {
        this.userService = userService;
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserRequestDTO userRequestDTO) {
        UserResponseDTO createdUser = userService.createUser(userRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        List<UserResponseDTO> users = userService.findAllUserResponseDTO();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{userId}/products")
    public ResponseEntity<List<ProductResponseDTO>> getProductByUser(@PathVariable UUID userId) {
        List<ProductResponseDTO> products = productService.findProductsByUserId(userId);
        return ResponseEntity.ok(products);
    }
}
