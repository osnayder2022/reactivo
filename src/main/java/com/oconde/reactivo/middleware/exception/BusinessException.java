package com.oconde.reactivo.middleware.exception;

import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;
import lombok.Data;

@Data
@EqualsAndHashCode(callSuper = true)
public class BusinessException extends RuntimeException {

    private final String code;
    private final HttpStatus status;

    public BusinessException(String code, String message, HttpStatus status) {
        super(message);
        this.code = code;
        this.status = status;
    }
}
