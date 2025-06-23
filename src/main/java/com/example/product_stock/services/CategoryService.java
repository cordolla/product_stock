package com.example.product_stock.services;

import com.example.product_stock.dtos.CategoryDTO;
import com.example.product_stock.dtos.CategoryRequestDTO;
import com.example.product_stock.dtos.CategoryResponseDTO;
import com.example.product_stock.entities.Category;
import com.example.product_stock.repositories.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public CategoryService(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    public List<CategoryDTO> findAllCategoriesDTO() {
        return categoryRepository.findAll().stream()
                .map(category -> modelMapper.map(category, CategoryDTO.class))
                .collect(Collectors.toList());
    }

    public Optional<CategoryResponseDTO> findCategoryResponseDTOById(UUID id) {
        return categoryRepository.findById(id)
                .map(category -> modelMapper.map(category, CategoryResponseDTO.class));
    }


    @Transactional
    public CategoryResponseDTO createCategory(CategoryRequestDTO requestDTO) {
        Category category = modelMapper.map(requestDTO, Category.class);
        category.setId(null);
        Category savedCategory = categoryRepository.save(category);
        return modelMapper.map(savedCategory, CategoryResponseDTO.class);
    }

    @Transactional
    public CategoryResponseDTO updateCategory(UUID id, CategoryRequestDTO requestDTO) {
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Category not found with ID: " + id));

        modelMapper.map(requestDTO, existingCategory);
        existingCategory.setId(id);

        Category updatedCategory = categoryRepository.save(existingCategory);
        return modelMapper.map(updatedCategory, CategoryResponseDTO.class);
    }

    @Transactional
    public void deleteCategory(UUID id) {
        if (!categoryRepository.existsById(id)) {
            throw new IllegalArgumentException("Category not found with ID: " + id);
        }
        categoryRepository.deleteById(id);
    }

    public List<CategoryResponseDTO> findAllCategoriesResponseDTO() {
        return categoryRepository.findAll().stream()
                .map(category -> modelMapper.map(category, CategoryResponseDTO.class))
                .collect(Collectors.toList());
    }
}