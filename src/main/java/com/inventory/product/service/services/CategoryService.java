package com.inventory.product.service.services;


import com.inventory.product.service.dto.CategoryRequestDto;
import com.inventory.product.service.dto.CategoryResponseDTO;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    CategoryResponseDTO addCategory(CategoryRequestDto categoryRequestDTO);
    CategoryResponseDTO editCategory(UUID id , CategoryRequestDto categoryRequestDTO);
    CategoryResponseDTO getCategory(UUID id);
    List<CategoryResponseDTO> getCategories();
    boolean deleteCategory(UUID id);
}
