package com.boxportation.exceptions.handler;

import org.springframework.http.HttpStatus;

import java.util.Date;

public class ErrorResponse {

    private Date timestamp;

    private Integer code;

    private String status;

    private String message;

    public ErrorResponse(){
        this.timestamp = new Date();
    }
    public ErrorResponse(HttpStatus status, String message){
        this();
        this.code = status.value();
        this.status = status.name();
        this.message = message;
    }
}
