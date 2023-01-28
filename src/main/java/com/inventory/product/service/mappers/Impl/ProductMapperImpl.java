package com.inventory.product.service.mappers.Impl;


import com.inventory.product.service.dto.ProductRequestDto;
import com.inventory.product.service.dto.ProductResponseDto;
import com.inventory.product.service.mappers.CategoryMapper;
import com.inventory.product.service.mappers.ProductMapper;
import com.inventory.product.service.mappers.SupplierMapper;
import com.inventory.product.service.models.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ProductMapperImpl implements ProductMapper {

    private CategoryMapper categoryMapper;
    private SupplierMapper supplierMapper;
//    private ModelMapper modelMapper;

    public ProductMapperImpl (CategoryMapper categoryMapper, SupplierMapper supplierMapper) {
        this.categoryMapper = categoryMapper;
        this.supplierMapper = supplierMapper;
//        this.modelMapper = modelMapper;
    }


    @Override
    public ProductResponseDto modelToDto(Product product) {

//        log.info(String.valueOf(product.getId()));
//        Set<SupplierResponseDto> suppliers = new HashSet<SupplierResponseDto>();
//        log.info("here starting ... 2");
////        product.getSuppliers().stream().map(supplier -> log.info(supplier.getName()));
        ProductResponseDto productResponseDto = ProductResponseDto.builder()
                .id(product.getId())
                .createdAt(product.getCreatedAt())
                .name(product.getName())
                .description(product.getDescription())
                .status(product.getStatus())
                .unit(product.getUnit())
                .category(this.categoryMapper.modelToDto(product.getCategory()))
                .updatedAt(product.getUpdatedAt())
                .build();
        return productResponseDto;
    }

    @Override
    public Product dtoToModel(ProductRequestDto requestDto) {

//        Product product = modelMapper.map(requestDto, Product.class);
    return  Product.builder()
            .status(requestDto.getStatus())
            .description(requestDto.getDescription())
            .name(requestDto.getName())
            .unit(requestDto.getUnit())
            .build();
//        return product;
    }
}
