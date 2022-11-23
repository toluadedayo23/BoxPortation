package com.boxportation.exceptions.handler;

import com.boxportation.exceptions.BoxException;
import com.boxportation.exceptions.ItemException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BoxException.class)
    public ErrorResponse handleBoxException(Exception e){
        BoxException boxException = (BoxException) e;

        HttpStatus status = HttpStatus.NOT_FOUND;

        log.error("Box with the TXREF: {}, not found", boxException.getFieldValue());

        return new ErrorResponse(status, boxException.getMessage());
    }

    @ExceptionHandler(ItemException.class)
    public ErrorResponse handleItemException(Exception e){
        ItemException itemException = (ItemException) e;

        HttpStatus status = HttpStatus.NOT_FOUND;

        log.error("Item with the ITEM ID: {}, not found", itemException.getFieldValue());

        return new ErrorResponse(status, itemException.getMessage());
    }
}
