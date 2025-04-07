package com.techqwerty.spring_boot_ecommerce_api_carrygo_store.exceptions;

import org.springframework.http.HttpStatus;

/*
 * Custom Exception for the applicaation 
 */
public class EcommerceAPIException extends RuntimeException {
    private HttpStatus status;
    private String message;

    public EcommerceAPIException(String message, HttpStatus status) {
        super();
        this.status = status;
        this.message = message;
    }

    public EcommerceAPIException(String message, HttpStatus status, String message1) {
        super(message);
        this.status = status;
        this.message = message1;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
