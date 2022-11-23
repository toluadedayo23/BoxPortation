package com.boxportation.dto;

import com.boxportation.model.BoxState;
import lombok.Data;

@Data
public class BoxResponses {

    private String txref;

    private BoxState state;

    private Double batteryCapacity;

    private double weightCarried;
}
