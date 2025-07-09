package com.example.product_stock.services;


import com.example.product_stock.dtos.ProductRequestDTO;
import com.example.product_stock.dtos.ProductResponseDTO;
import com.example.product_stock.entities.Category;
import com.example.product_stock.entities.Product;
import com.example.product_stock.entities.Supplier;
import com.example.product_stock.repositories.CategoryRepository;
import com.example.product_stock.repositories.ProductRepository;

import com.example.product_stock.repositories.SupplierRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductService {


    private final ProductRepository productRepository;
    private final SupplierRepository supplierRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public ProductService(ProductRepository productRepository, SupplierRepository supplierRepository, CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.supplierRepository = supplierRepository;
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    public List<ProductResponseDTO> findAllProductsDTO() {
        return productRepository.findAll().stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public Optional<ProductResponseDTO> findProductDTOById(UUID id){
        return productRepository.findById(id)
                .map(this::convertToResponseDTO);
    }

    @Transactional
    public ProductResponseDTO createProduct(ProductRequestDTO requestDTO) {
        Supplier supplier = supplierRepository.findById(requestDTO.getSupplierId())
                .orElseThrow(() -> new IllegalArgumentException("Supplier not found with ID: " + requestDTO.getSupplierId()));
        Category category = categoryRepository.findById(requestDTO.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Category not found with ID: " + requestDTO.getCategoryId()));

        Product product = modelMapper.map(requestDTO, Product.class);
        product.setSupplier(supplier);
        product.setCategory(category);
        product.setId(null);

        Product savedProduct = productRepository.save(product);
        return convertToResponseDTO(savedProduct);
    }

    @Transactional
    public ProductResponseDTO updateProduct(UUID id, ProductRequestDTO requestDTO) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with ID: " + id));
        if (requestDTO.getSupplierId() != null && !requestDTO.getSupplierId().equals(existingProduct.getSupplier().getId())) {
            Supplier supplier = supplierRepository.findById(requestDTO.getSupplierId())
                    .orElseThrow(() -> new IllegalArgumentException("Supplier not found with ID: " + requestDTO.getSupplierId()));
            existingProduct.setSupplier(supplier);
        }
        if (requestDTO.getCategoryId() != null && !requestDTO.getCategoryId().equals(existingProduct.getCategory().getId())) {
            Category category = categoryRepository.findById(requestDTO.getCategoryId())
                    .orElseThrow(() -> new IllegalArgumentException("Category not found with ID: " + requestDTO.getCategoryId()));
            existingProduct.setCategory(category);
        }

        modelMapper.map(requestDTO, existingProduct);

        Product updatedProduct = productRepository.save(existingProduct);
        return convertToResponseDTO(updatedProduct);
    }

    public void deleteProduct(UUID id) {
        if (!productRepository.existsById(id)) {
            throw new IllegalArgumentException("Product not found with ID: " + id);
        }
        productRepository.deleteById(id);
    }


    private ProductResponseDTO convertToResponseDTO(Product product) {
        ProductResponseDTO dto = modelMapper.map(product, ProductResponseDTO.class);

        return dto;
    }


}
