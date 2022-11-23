package com.boxportation.dto;

import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class ItemsDto {

    @NotBlank(message = "Item name cannot be blank")
    @Pattern(regexp = "^[A-Za-z0-9-_]+$", message = "allowed only letters, numbers, ‘-‘, ‘_’")
    private String name;

    @DecimalMin("0.1")
    private Double weight;

    @NotBlank(message = "Item code cannot be blank")
    @Pattern(regexp = "^[A-Z0-9_]+$")
    private String code;


}
