package com.boxportation.dto;

import lombok.Data;


@Data
public class LoadedItemsResponse {

    private Long boxId;

    private String name;

    private Double weight;

    private String status;

}
