package com.inventory.product.service.services.impl;


import com.inventory.product.service.dto.ProductRequestDto;
import com.inventory.product.service.dto.ProductResponseDto;
import com.inventory.product.service.mappers.ProductMapper;
import com.inventory.product.service.models.Category;
import com.inventory.product.service.models.Product;
import com.inventory.product.service.models.Supplier;
import com.inventory.product.service.repositories.CategoryRepository;
import com.inventory.product.service.repositories.ProductRepository;
import com.inventory.product.service.repositories.SupplierRepository;
import com.inventory.product.service.services.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
       /* log.info("here starting ...");
        List<ProductResponseDto> productResponseDTOS = new ArrayList<>();
        productRepository.findAll().forEach(p -> {
            if(p.getDeletedAt() == null){
                productResponseDTOS.add(productMapper.modelToDto(p));
            }
        });
        return productResponseDTOS;*/
        List<Product> products = productRepository.findAll();
        return products.stream().map(productMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public ProductResponseDto getProduct(UUID id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found with id " + id.toString()));
        return productMapper.toDto(product);

        /*Product product = productRepository.findById(id).orElse(null);
        if (product != null && product.getDeletedAt() == null){
            ProductResponseDto productResponseDto = productMapper.modelToDto(product);
            return  productResponseDto;
        }
        return null;*/
    }

    @Override
    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) {
        Category category = categoryRepository.findById(productRequestDto.getCategory())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found with id " + productRequestDto.getCategory().toString()));

        List<Supplier> suppliers = productRequestDto.getSuppliers().stream()
                .map(s -> supplierRepository.findById(s)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Supplier not found with id " + s.toString())))
                .collect(Collectors.toList());

        Product product = productMapper.toEntity(productRequestDto);
        product.setCategory(category);
        product.setSuppliers(suppliers);
        return productMapper.toDto(productRepository.save(product));
        /*
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
*/
    }

    @Override
    public ProductResponseDto editProduct(UUID id, ProductRequestDto productRequestDto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found with id " + id));

        Category category = categoryRepository.findById(productRequestDto.getCategory())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found with id " + productRequestDto.getCategory().toString()));

        List<Supplier> suppliers = productRequestDto.getSuppliers().stream()
                .map(s -> supplierRepository.findById(s)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Supplier not found with id " + s.toString())))
                .collect(Collectors.toList());

        product.setName(productRequestDto.getName());
        product.setDescription(productRequestDto.getDescription());
        product.setCategory(category);
        product.setSuppliers(suppliers);
        return productMapper.toDto(productRepository.save(product));
       /* Product product = productRepository.findById(id).orElse(null);
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
        return null;*/
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
