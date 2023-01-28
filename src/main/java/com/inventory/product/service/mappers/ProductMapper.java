package com.inventory.product.service.mappers;


import com.inventory.product.service.dto.ProductRequestDto;
import com.inventory.product.service.dto.ProductResponseDto;
import com.inventory.product.service.models.Product;

public interface ProductMapper {
    ProductResponseDto modelToDto(Product product);
    Product dtoToModel (ProductRequestDto requestDto);
}

