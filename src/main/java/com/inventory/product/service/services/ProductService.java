package com.inventory.product.service.services;

import com.inventory.product.service.dto.ProductRequestDto;
import com.inventory.product.service.dto.ProductResponseDto;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    List<ProductResponseDto> getProducts();
    ProductResponseDto getProduct(UUID id);
    ProductResponseDto addProduct(ProductRequestDto product);
    ProductResponseDto editProduct(UUID id, ProductRequestDto product);
    boolean deleteProduct(UUID id);
    boolean checkExistence(UUID id);
}
