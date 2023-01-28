package com.inventory.product.service.dto;

import com.inventory.product.service.enums.Status;
import com.inventory.product.service.enums.Unit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ProductResponseDto {
    private UUID id;
    private String name;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Status status;
    private Unit unit;
    private CategoryResponseDTO category;
//    private Set<Supplier> suppliers = new HashSet<>();
}
