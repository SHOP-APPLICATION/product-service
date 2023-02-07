package com.inventory.product.service.services.impl;


import com.inventory.product.service.dto.CategoryRequestDto;
import com.inventory.product.service.dto.CategoryResponseDTO;
import com.inventory.product.service.mappers.CategoryMapper;
import com.inventory.product.service.models.Category;
import com.inventory.product.service.repositories.CategoryRepository;
import com.inventory.product.service.services.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryMapper categoryMapper;


    @Override
    public CategoryResponseDTO addCategory(CategoryRequestDto categoryRequestDTO) {
        Category category = categoryMapper.toEntity(categoryRequestDTO);
        Category categorySaved = categoryRepository.save(category);
        return categoryMapper.toDto(categorySaved);
    }

    @Override
    public CategoryResponseDTO editCategory(UUID id, CategoryRequestDto categoryRequestDTO) {
        Category category = categoryRepository.findById(id).orElse(null);
        if (category != null && category.getDeletedAt() == null) {
            category.setName(categoryRequestDTO.getName());
            category.setDescription(categoryRequestDTO.getDescription());
            category.setStatus(categoryRequestDTO.getStatus());
            category = categoryRepository.save(category);
            return categoryMapper.toDto(category);
        }
        return null;
    }

    @Override
    public CategoryResponseDTO getCategory(UUID id) {
        Optional<Category> category = categoryRepository.findById(id);
        return category.map(categoryMapper::toDto).orElse(null);
    }

    @Override
    public List<CategoryResponseDTO> getCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(categoryMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public boolean deleteCategory(UUID id) {
        Category category = categoryRepository.findById(id).orElse(null);
        if (category != null) {
            category.setDeletedAt(LocalDateTime.now());
            categoryRepository.save(category);
            return true;
        }
       return false;
    }
}
