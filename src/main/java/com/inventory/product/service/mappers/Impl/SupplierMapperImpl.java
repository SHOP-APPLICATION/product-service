package com.inventory.product.service.mappers.Impl;


import com.inventory.product.service.dto.SupplierRequestDto;
import com.inventory.product.service.dto.SupplierResponseDto;
import com.inventory.product.service.mappers.SupplierMapper;
import com.inventory.product.service.models.Supplier;
import org.springframework.stereotype.Component;

@Component
public class SupplierMapperImpl implements SupplierMapper {

//    @Autowired
//    ModelMapper modelMapper;

    @Override
    public SupplierResponseDto modelToDto(Supplier supplier) {
        SupplierResponseDto supplierResponseDto = SupplierResponseDto.builder()
                .id(supplier.getId())
                .createdAt(supplier.getCreatedAt())
                .name(supplier.getName())
                .status(supplier.getStatus())
                .updatedAt(supplier.getUpdatedAt())
                .raison(supplier.getRaison())
                .build();
        return supplierResponseDto;
    }

    @Override
    public Supplier dtoToModel(SupplierRequestDto requestDto) {
        Supplier supplier = Supplier.builder()
                .status(requestDto.getStatus())
                .name(requestDto.getName())
                .raison(requestDto.getRaison())
                .build();
        return supplier;
    }
}
