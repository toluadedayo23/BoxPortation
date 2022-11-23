package com.boxportation.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class LoadBoxDto {


    @NotBlank
    @Size(max=20, message = "txref cannot be more than 20 charaters")
    private String txref;

    @Range(min = 1, message = "incorrect Item Id")
    private Long itemId;
}
