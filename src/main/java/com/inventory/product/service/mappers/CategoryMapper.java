package com.inventory.product.service.mappers;

import com.inventory.product.service.dto.CategoryRequestDto;
import com.inventory.product.service.dto.CategoryResponseDTO;
import com.inventory.product.service.models.Category;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    private final ModelMapper modelMapper;

    @Autowired
    public CategoryMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public CategoryResponseDTO toDto(Category category) {
        return modelMapper.map(category, CategoryResponseDTO.class);
    }

    public Category toEntity(CategoryRequestDto categoryDto) {
        return modelMapper.map(categoryDto, Category.class);
    }
}
