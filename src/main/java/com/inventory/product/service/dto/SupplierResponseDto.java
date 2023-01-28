package com.inventory.product.service.dto;

import com.inventory.product.service.enums.Status;
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
public class SupplierResponseDto {
    private UUID id;
    private String name;
    private String raison;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Status status;
}
