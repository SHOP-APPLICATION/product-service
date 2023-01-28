package com.inventory.product.service.services;

import com.inventory.product.service.dto.SupplierRequestDto;
import com.inventory.product.service.dto.SupplierResponseDto;

import java.util.List;
import java.util.UUID;

public interface SupplierService {
    List<SupplierResponseDto> getSuppliers();
    SupplierResponseDto getSupplier(UUID id);
    SupplierResponseDto addSupplier(SupplierRequestDto supplier);
    SupplierResponseDto editSupplier(UUID id, SupplierRequestDto supplier);
    boolean deleteSupplier(UUID id);
    boolean checkExistence(UUID id);
}
