package com.inventory.product.service.dto;


import com.inventory.product.service.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class SupplierRequestDto {
    @NotNull
    @NotBlank
    private String name;
    private String raison;
    @NotNull
    private Status status;
}
