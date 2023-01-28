package com.inventory.product.service.services.impl;


import com.inventory.product.service.dto.ProductRequestDto;
import com.inventory.product.service.dto.ProductResponseDto;
import com.inventory.product.service.mappers.ProductMapper;
import com.inventory.product.service.models.Category;
import com.inventory.product.service.models.Product;
import com.inventory.product.service.repositories.CategoryRepository;
import com.inventory.product.service.repositories.ProductRepository;
import com.inventory.product.service.repositories.SupplierRepository;
import com.inventory.product.service.services.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    SupplierRepository supplierRepository;
    @Autowired
    ProductMapper productMapper;

    @Override
    public List<ProductResponseDto> getProducts() {
        log.info("here starting ...");
        List<ProductResponseDto> productResponseDTOS = new ArrayList<>();
        productRepository.findAll().forEach(p -> {
            if(p.getDeletedAt() == null){
                productResponseDTOS.add(productMapper.modelToDto(p));
            }
        });
        return productResponseDTOS;
    }

    @Override
    public ProductResponseDto getProduct(UUID id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product != null && product.getDeletedAt() == null){
            ProductResponseDto productResponseDto = productMapper.modelToDto(product);
            return  productResponseDto;
        }
        return null;
    }

    @Override
    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) {

        log.info("i m hered for now");
        Product  product = productMapper.dtoToModel(productRequestDto);

        Category category = categoryRepository.findById(UUID.fromString(productRequestDto.getCategory())).orElse(null) ;
        if (category == null) log.info("Category with id s% not found", productRequestDto.getCategory());
        product.setCategory(category);
        product.setId(UUID.randomUUID());
        // supplier
//        for (String id : productRequestDto.getSuppliers())
//        {
//            Supplier supplier = supplierRepository.findById(UUID.fromString(id)).orElse(null);
//            product.getSuppliers().add(supplier);
//        }
//        log.info(String.valueOf(product.getSuppliers().size()));
        ProductResponseDto productResponseDto =  productMapper.modelToDto(productRepository.save(product));
        return productResponseDto;

    }

    @Override
    public ProductResponseDto editProduct(UUID id, ProductRequestDto productRequestDto) {
        Product product = productRepository.findById(id).orElse(null);
        if (product != null && product.getDeletedAt() == null) {
            product.setName(productRequestDto.getName());
            product.setDescription(productRequestDto.getDescription());
            product.setStatus(productRequestDto.getStatus());
            product.setUnit(productRequestDto.getUnit());
            Category category = categoryRepository.findById(UUID.fromString(productRequestDto.getCategory())).orElse(null) ;
            if (category == null) {
                log.info("Category with id s% not found", productRequestDto.getCategory());
            }
            product.setCategory(category);
            // supplier
            ProductResponseDto productResponseDto =  productMapper.modelToDto(productRepository.save(product));
            return productResponseDto;
        }
        return null;
    }

    @Override
    public boolean deleteProduct(UUID id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product != null) {
            product.setDeletedAt(LocalDateTime.now());
            productRepository.save(product);
            return true;
        }
        return false;
    }

    @Override
    public boolean checkExistence(UUID id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product != null) {
            log.info("FOund");

            return true;
        }
        log.info("Not Found");

        return false;
    }
}
