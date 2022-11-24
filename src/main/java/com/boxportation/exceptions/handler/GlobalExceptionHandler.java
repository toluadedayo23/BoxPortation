package com.boxportation.exceptions.handler;

import com.boxportation.exceptions.BoxException;
import com.boxportation.exceptions.ItemException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String errorMessage = ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
        List<String> validationList = ex.getBindingResult().getFieldErrors().stream().map(fieldError -> fieldError.getDefaultMessage()).collect(Collectors.toList());
        log.error("Validation error: " + validationList);
        return new ResponseEntity<>(new ErrorResponse(status, errorMessage), status);
    }

    @ExceptionHandler(Exception.class)
    public ErrorResponse handleException(Exception e) {

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        log.error("Internal Error");

        return new ErrorResponse(status, e.getMessage());
    }
}
