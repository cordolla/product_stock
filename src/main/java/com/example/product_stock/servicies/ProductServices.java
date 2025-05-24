package com.example.product_stock.servicies;

import com.example.product_stock.dto.ProductDTO;
import com.example.product_stock.entities.Product;
import com.example.product_stock.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServices {

    @Autowired
    private ProductRepository productRepository;

    public Product save(ProductDTO dto){
        Product product = new Product();
        product.setName(dto.getName());
        product.setCurrentStock(dto.getCurrentStock());
        product.setCategoryId(dto.getCategoryId());
        product.setDescription(dto.getDescription());
        product.setSupplyerId(dto.getSupplyerId());
        return productRepository.save(product);
    }

    public List<ProductDTO> findAll(){
        List<Product> result = productRepository.findAll();
        return result.stream().map(x -> new ProductDTO(x)).toList();
    }
}
