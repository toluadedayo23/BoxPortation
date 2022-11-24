package com.boxportation.exceptions.handler;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Getter
@Setter
@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
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
