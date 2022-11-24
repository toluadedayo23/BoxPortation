package com.boxportation.dto;

import lombok.Data;


@Data
public class LoadedItemsResponse {

    private String boxTxref;

    private String itemName;

    private Double itemWeight;

    private String itemStatus;

}
