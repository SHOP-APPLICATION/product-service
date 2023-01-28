package com.inventory.product.service.mappers.Impl;


import com.inventory.product.service.dto.CategoryRequestDto;
import com.inventory.product.service.dto.CategoryResponseDTO;
import com.inventory.product.service.mappers.CategoryMapper;
import com.inventory.product.service.models.Category;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapperIpml implements CategoryMapper {
    @Override
    public CategoryResponseDTO modelToDto(Category category) {

        CategoryResponseDTO categoryResponseDTO = CategoryResponseDTO.builder()
                .id(category.getId())
                .createdAt(category.getCreatedAt())
                .name(category.getName())
                .description(category.getDescription())
                .status(category.getStatus())
                .updatedAt(category.getUpdatedAt())
                .build();
        return categoryResponseDTO;
    }

    @Override
    public Category dtoToModel(CategoryRequestDto requestDTO) {
        Category category = new Category();
        BeanUtils.copyProperties(requestDTO, category);
        return category;
    }
}
