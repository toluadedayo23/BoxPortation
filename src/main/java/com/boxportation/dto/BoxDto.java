package com.boxportation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class BoxDto {

    @NotBlank
    @Size(max=20, message = "txref cannot be more than 20 charaters")
    private String txref;


    @Range(min = 0, max = 100, message = "Battery capacity ranges between 0 and 100")
    private Double batteryCapacity;
}
