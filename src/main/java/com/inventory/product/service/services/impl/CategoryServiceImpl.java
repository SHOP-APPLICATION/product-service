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
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
        Category category = categoryMapper.dtoToModel(categoryRequestDTO);
        Category categorySaved = categoryRepository.save(category);
        CategoryResponseDTO categoryResponseDTO = categoryMapper.modelToDto(categorySaved);
        return categoryResponseDTO;
    }

    @Override
    public CategoryResponseDTO editCategory(UUID id, CategoryRequestDto categoryRequestDTO) {
        Category category = categoryRepository.findById(id).orElse(null);
        if (category != null && category.getDeletedAt() == null) {
            category.setName(categoryRequestDTO.getName());
            category.setDescription(categoryRequestDTO.getDescription());
            category.setStatus(categoryRequestDTO.getStatus());
            CategoryResponseDTO categoryResponseDTO = categoryMapper.modelToDto(categoryRepository.save(category));
            return categoryResponseDTO;
        }
        return null;
    }

    @Override
    public CategoryResponseDTO getCategory(UUID id) {
        Category category = categoryRepository.findById(id).orElse(null);
        if (category != null && category.getDeletedAt() == null){
            CategoryResponseDTO categoryResponseDTO = categoryMapper.modelToDto(category);
            return  categoryResponseDTO;
        }
        return null;
    }

    @Override
    public List<CategoryResponseDTO> getCategories() {
        List<CategoryResponseDTO> categoryResponseDTOS = new ArrayList<>();
        categoryRepository.findAll().forEach(c -> {
            if(c.getDeletedAt() == null){
                categoryResponseDTOS.add(categoryMapper.modelToDto(c));
            }
        });
        return categoryResponseDTOS;
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
