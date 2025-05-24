package com.example.product_stock.servicies;

import com.example.product_stock.dto.SupplyerDTO;
import com.example.product_stock.entities.Supplyer;
import com.example.product_stock.repositories.SupplyerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SupplyerServices {

    @Autowired
    private SupplyerRepository supplyerRepository;

    public Supplyer save(SupplyerDTO dto) {
        Supplyer supplyer = new Supplyer();
        supplyer.setName(dto.getName());
        supplyer.setCnpj(dto.getCnpj());
        supplyer.setEmail(dto.getEmail());
        supplyer.setPhone(dto.getPhone());
        supplyer.setAddress(dto.getAddress());
        return supplyerRepository.save(supplyer);
    }

}
