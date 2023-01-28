package com.inventory.product.service.mappers;


import com.inventory.product.service.dto.SupplierRequestDto;
import com.inventory.product.service.dto.SupplierResponseDto;
import com.inventory.product.service.models.Supplier;

public interface SupplierMapper {
    SupplierResponseDto modelToDto(Supplier supplier);
    Supplier dtoToModel (SupplierRequestDto requestDto);
}
