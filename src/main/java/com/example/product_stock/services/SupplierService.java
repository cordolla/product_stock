package com.example.product_stock.services;

import com.example.product_stock.dtos.SupplierDTO;
import com.example.product_stock.dtos.SupplierRequestDTO;
import com.example.product_stock.dtos.SupplierResponseDTO;
import com.example.product_stock.entities.Supplier;
import com.example.product_stock.entities.User;
import com.example.product_stock.exceptions.SupplierFoundException;
import com.example.product_stock.repositories.SupplierRepository;
import com.example.product_stock.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SupplierService {

    private final SupplierRepository supplierRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    public SupplierService(SupplierRepository supplierRepository, ModelMapper modelMapper, UserRepository userRepository) {
        this.supplierRepository = supplierRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    public List<SupplierDTO> findAllSuppliersDTO() {
        return supplierRepository.findAll().stream()
                .map(supplier -> modelMapper.map(supplier, SupplierDTO.class))
                .collect(Collectors.toList());
    }

    public Optional<SupplierResponseDTO> findSupplierResponseDTOById(UUID id) {
        return supplierRepository.findById(id)
                .map(supplier -> modelMapper.map(supplier, SupplierResponseDTO.class));
    }

    @Transactional
    public SupplierResponseDTO createSupplier(SupplierRequestDTO requestDTO) {
        if (supplierRepository.findByCpnjOrEmail(requestDTO.getCnpj(), requestDTO.getEmail()).isPresent()) {
            throw new SupplierFoundException();
        }

        Supplier supplier = modelMapper.map(requestDTO, Supplier.class);
        User user = userRepository.findById(requestDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("usuario não encontrado"));
        supplier.setUser(user);
        supplier.setId(null);
        Supplier savedSupplier = supplierRepository.save(supplier);

        return modelMapper.map(savedSupplier, SupplierResponseDTO.class);
    }

    @Transactional
    public SupplierResponseDTO updateSupplier(UUID id, SupplierRequestDTO requestDTO) {
        Supplier existingSupplier = supplierRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Supplier not found with ID: " + id));

        modelMapper.map(requestDTO, existingSupplier);
        existingSupplier.setId(id);

        Supplier updatedSupplier = supplierRepository.save(existingSupplier);
        return modelMapper.map(updatedSupplier, SupplierResponseDTO.class);
    }


    @Transactional
    public void deleteSupplier(UUID id) {
        if (!supplierRepository.existsById(id)) {
            throw new IllegalArgumentException("Supplier not found with ID: " + id);
        }
        supplierRepository.deleteById(id);
    }


    public List<SupplierResponseDTO> findAllSuppliersResponseDTO() {
        return supplierRepository.findAll().stream()
                .map(supplier -> modelMapper.map(supplier, SupplierResponseDTO.class))
                .collect(Collectors.toList());
    }
}