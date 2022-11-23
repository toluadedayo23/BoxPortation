package com.boxportation.exceptions;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoxException extends RuntimeException {

    private String resourcesName;

    private Object fieldValue;

    public BoxException(String resourcesName, Object fieldValue){
        super(String.format("%s with the TXREF: %s, not found", resourcesName, fieldValue));
        this.resourcesName = resourcesName;
        this.fieldValue = fieldValue;
    }

    public BoxException(String message){
        super(message);
    }
}
