package com.boxportation.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemException extends RuntimeException {

    private String resourcesName;

    private Object fieldValue;

    public ItemException(String resourcesName, Object fieldValue){
        super(String.format("%s with the ITEM ID: %s, not found", resourcesName, fieldValue));
        this.resourcesName = resourcesName;
        this.fieldValue = fieldValue;
    }

    public ItemException(String message){
        super(message);
    }
}
