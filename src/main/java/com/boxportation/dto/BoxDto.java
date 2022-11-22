package com.boxportation.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class BoxDto {

    @NotBlank
    @Size(max=20, message = "txref cannot be more than 20 charaters")
    private String txref;

    @Range(max = 500, message = "weight cannot be more than 500gr")
    private Double weightLimit;

    @Range(min = 0, max = 100, message = "Battery capacity ranges between 0 and 100")
    private Double batteryCapacity;
}
