package com.inventory.product.service.dto;


import com.inventory.product.service.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryRequestDto {
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

}
