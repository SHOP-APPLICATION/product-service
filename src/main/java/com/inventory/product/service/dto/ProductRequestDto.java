package com.inventory.product.service.dto;


import com.inventory.product.service.enums.Status;
import com.inventory.product.service.enums.Unit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductRequestDto {
    @NotNull
    @NotBlank
    @Size(min = 3)
    private String name;
    @NotNull
    @NotBlank
    @Size(min = 3)
    private String description;
    @NotNull
    private Status status;
    @NotNull
    private Unit unit;
    @NotNull
    private UUID category;
    private List<UUID> suppliers;
}
