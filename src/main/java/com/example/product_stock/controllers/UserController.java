package com.example.product_stock.controllers;

import com.example.product_stock.dtos.ProductResponseDTO;
import com.example.product_stock.dtos.UserRequestDTO;
import com.example.product_stock.dtos.UserResponseDTO;
import com.example.product_stock.entities.User;
import com.example.product_stock.repositories.UserRepository;
import com.example.product_stock.services.UserService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

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
        User user = userRepository.findById(userId)
                .orElseThrow(() ->  new RuntimeException("Usuario não encontrado"));

        List<ProductResponseDTO> products = user.getSuppliers().stream()
                .flatMap(supplier -> supplier.getProducts().stream())
                .map(product -> {
                    ProductResponseDTO dto = modelMapper.map(product, ProductResponseDTO.class);
                    dto.setSupplierName(product.getSupplier().getName());
                    return dto;
                })
                .toList();
        return ResponseEntity.ok(products);
    }
}
