package com.example.product_stock.services;

import com.example.product_stock.dto.CreateUserRequestDTO;
import com.example.product_stock.dto.UserResponseDTO;
import com.example.product_stock.entities.UserEntity;
import com.example.product_stock.exceptions.UserFoundException;
import com.example.product_stock.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateUserService {

    @Autowired
    private UserRepository userRepository;

    public UserResponseDTO execute(CreateUserRequestDTO createUserRequestDTO) {
        userRepository.findByUsernameOrEmail(createUserRequestDTO.getUsername(), createUserRequestDTO.getEmail())
                .ifPresent((user) -> {
                    throw new UserFoundException();
                });
        UserEntity userEntity = new UserEntity();
        userEntity.setName(createUserRequestDTO.getName());
        userEntity.setUsername(createUserRequestDTO.getUsername());
        userEntity.setPassword(createUserRequestDTO.getPassword());
        userEntity.setEmail(createUserRequestDTO.getEmail());

        UserEntity savedUser = userRepository.save(userEntity);

        return new UserResponseDTO(
                savedUser.getName(),
                savedUser.getUsername()
        );
    }
}
