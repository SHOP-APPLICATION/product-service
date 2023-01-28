package com.inventory.product.service.services.impl;


import com.inventory.product.service.dto.SupplierRequestDto;
import com.inventory.product.service.dto.SupplierResponseDto;
import com.inventory.product.service.mappers.SupplierMapper;
import com.inventory.product.service.models.Supplier;
import com.inventory.product.service.repositories.SupplierRepository;
import com.inventory.product.service.services.SupplierService;
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
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    SupplierRepository supplierRepository;

    @Autowired
    SupplierMapper supplierMapper;

    @Override
    public List<SupplierResponseDto> getSuppliers() {

        List<SupplierResponseDto> supplierResponseDtos= new ArrayList<>();
        supplierRepository.findAll().forEach(s -> {
            if(s.getDeletedAt() == null){
                supplierResponseDtos.add(supplierMapper.modelToDto(s));
            }
        });
        return supplierResponseDtos;
    }

    @Override
    public SupplierResponseDto getSupplier(UUID id) {
        Supplier supplier = supplierRepository.findById(id).orElse(null);
        if (supplier != null && supplier.getDeletedAt() == null){
            SupplierResponseDto supplierResponseDto = supplierMapper.modelToDto(supplier);
            return  supplierResponseDto;
        }
        return null;
    }

    @Override
    public SupplierResponseDto addSupplier(SupplierRequestDto supplierRequestDto) {

        Supplier supplier = supplierMapper.dtoToModel(supplierRequestDto);
        supplier.setId(UUID.randomUUID());
        Supplier supplierSaved = supplierRepository.save(supplier);
        SupplierResponseDto supplierResponseDto = supplierMapper.modelToDto(supplierSaved);
        return supplierResponseDto;
    }

    @Override
    public SupplierResponseDto editSupplier(UUID id, SupplierRequestDto supplierRequestDto) {

        Supplier supplier = supplierRepository.findById(id).orElse(null);
        if (supplier != null && supplier.getDeletedAt() == null) {
            supplier.setName(supplierRequestDto.getName());
            supplier.setRaison(supplierRequestDto.getRaison());
            supplier.setStatus(supplierRequestDto.getStatus());
            SupplierResponseDto supplierResponseDto = supplierMapper.modelToDto(supplierRepository.save(supplier));
            return supplierResponseDto;
        }
        return null;
    }

    @Override
    public boolean checkExistence(UUID id) {
        Supplier supplier = supplierRepository.findById(id).orElse(null);
        if (supplier != null) {
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteSupplier(UUID id) {

        Supplier supplier = supplierRepository.findById(id).orElse(null);
        if (supplier != null) {
            supplier.setDeletedAt(LocalDateTime.now());
            supplierRepository.save(supplier);
            return true;
        }
        return false;
    }
}
