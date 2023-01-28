package com.inventory.product.service.mappers;

import com.inventory.product.service.dto.CategoryRequestDto;
import com.inventory.product.service.dto.CategoryResponseDTO;
import com.inventory.product.service.models.Category;

//@Mapper // mapstruct
public interface CategoryMapper {
    CategoryResponseDTO modelToDto(Category category);
    Category dtoToModel (CategoryRequestDto requestDTO);
}
