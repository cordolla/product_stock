package com.example.product_stock.controllers;

import com.example.product_stock.dto.CreateUserRequestDTO;
import com.example.product_stock.dto.ProductResponseDTO;
import com.example.product_stock.dto.UserResponseDTO;
import com.example.product_stock.entities.UserEntity;
import com.example.product_stock.services.CreateUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private CreateUserService createUserService;

    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody CreateUserRequestDTO createUserRequestDTO) {
        try {
            UserResponseDTO result = createUserService.execute(createUserRequestDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
