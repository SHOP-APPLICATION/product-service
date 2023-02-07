package com.inventory.product.service.mappers;

import com.inventory.product.service.dto.ProductRequestDto;
import com.inventory.product.service.dto.ProductResponseDto;
import com.inventory.product.service.models.Product;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ProductMapper {
    private final ModelMapper modelMapper;
    private final SupplierMapper supplierMapper;
    private final CategoryMapper categoryMapper;
    @Autowired
    public ProductMapper(ModelMapper modelMapper, SupplierMapper supplierMapper, CategoryMapper categoryMapper) {
        this.modelMapper = modelMapper;
        this.supplierMapper = supplierMapper;
        this.categoryMapper = categoryMapper;
    }

    public ProductResponseDto toDto(Product product) {
        ProductResponseDto productDto = modelMapper.map(product, ProductResponseDto.class);
        productDto.setSuppliers(product.getSuppliers().stream().map(supplierMapper::toDto).collect(Collectors.toList()));
        productDto.setCategory(categoryMapper.toDto(product.getCategory()));
        return productDto;
    }

    public Product toEntity(ProductRequestDto productDto) {
        return modelMapper.map(productDto, Product.class);
    }
}
