package com.techqwerty.spring_boot_ecommerce_api_carrygo_store.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class NameAlreadyException extends RuntimeException {
    private String name;

    public NameAlreadyException(String message) {
        super(message);
    }
}
