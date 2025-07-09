package com.example.product_stock.config;

import com.example.product_stock.dtos.CategoryDTO;
import com.example.product_stock.dtos.CategoryRequestDTO;
import com.example.product_stock.dtos.CategoryResponseDTO;
import com.example.product_stock.dtos.ProductRequestDTO;
import com.example.product_stock.dtos.ProductResponseDTO;
import com.example.product_stock.dtos.SupplierDTO;
import com.example.product_stock.dtos.SupplierRequestDTO;
import com.example.product_stock.dtos.SupplierResponseDTO;
import com.example.product_stock.entities.Category;
import com.example.product_stock.entities.Product;
import com.example.product_stock.entities.Supplier;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setAmbiguityIgnored(true);

        modelMapper.createTypeMap(ProductRequestDTO.class, Product.class)
                .addMappings(mapper -> {
                    mapper.skip(Product::setId);
                    mapper.skip(Product::setSupplier);
                    mapper.skip(Product::setCategory);
                });

        modelMapper.createTypeMap(Product.class, ProductResponseDTO.class)
                        .addMappings(mapper -> {
                            mapper.map(src -> src.getSupplier().getName(), ProductResponseDTO::setSupplierName);
                            mapper.map(src -> src.getCategory().getName(), ProductResponseDTO::setCategoryName);
                        });

        modelMapper.createTypeMap(Supplier.class, SupplierDTO.class);
        modelMapper.createTypeMap(SupplierRequestDTO.class, Supplier.class);
        modelMapper.createTypeMap(Supplier.class, SupplierResponseDTO.class);

        modelMapper.createTypeMap(Category.class, CategoryDTO.class);
        modelMapper.createTypeMap(CategoryRequestDTO.class, Category.class);
        modelMapper.createTypeMap(Category.class, CategoryResponseDTO.class);

        return modelMapper;
    }
}