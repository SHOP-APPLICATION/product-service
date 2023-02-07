package com.inventory.product.service.mappers;

import com.inventory.product.service.dto.SupplierRequestDto;
import com.inventory.product.service.dto.SupplierResponseDto;
import com.inventory.product.service.models.Supplier;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SupplierMapper {
    private final ModelMapper modelMapper;

    @Autowired
    public SupplierMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public SupplierResponseDto toDto(Supplier supplier) {
        return modelMapper.map(supplier, SupplierResponseDto.class);
    }

    public Supplier toEntity(SupplierRequestDto supplierDto) {
        return modelMapper.map(supplierDto, Supplier.class);
    }
}
