package com.example.product_stock.services;

import com.example.product_stock.dtos.UserRequestDTO;
import com.example.product_stock.dtos.UserResponseDTO;
import com.example.product_stock.entities.User;
import com.example.product_stock.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public UserResponseDTO createUser(UserRequestDTO requestDTO){
        User user = modelMapper.map(requestDTO, User.class);
        user.setId(null);
        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserResponseDTO.class);
    }

    public List<UserResponseDTO> findAllUserResponseDTO(){
        return userRepository.findAll().stream()
                .map(user -> modelMapper.map(user, UserResponseDTO.class))
                .collect(Collectors.toList());
    }
}
