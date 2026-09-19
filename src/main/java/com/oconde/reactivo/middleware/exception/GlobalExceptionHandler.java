package com.oconde.reactivo.middleware.exception;


import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ServerWebExchange;
import com.oconde.reactivo.model.dto.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import reactor.core.publisher.Mono;
import lombok.extern.slf4j.Slf4j;
import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Intercepta las excepciones de negocio personalizadas.
     */
    @ExceptionHandler(BusinessException.class)
    public Mono<ResponseEntity<ErrorResponse>> handleBusinessException(BusinessException ex, ServerWebExchange exchange) {

        ServerHttpRequest request = exchange.getRequest();
        log.warn("Excepción de negocio en [{} {}]: Code: {}, Message: {}", request.getMethod(), request.getURI().getPath(), ex.getCode(), ex.getMessage());

        ErrorResponse errorResponse = new ErrorResponse(
                ex.getCode(),
                ex.getMessage(),
                ex.getStatus().value(),
                request.getURI().getPath(),
                LocalDateTime.now()
        );

        return Mono.just(ResponseEntity.status(ex.getStatus()).body(errorResponse));
    }

    /**
     * Intercepta excepciones de la aplicación no controladas (NullPointerException, Server Errors, etc.).
     */
    @ExceptionHandler(Throwable.class)
    public Mono<ResponseEntity<ErrorResponse>> handleGeneralException(Throwable ex, ServerWebExchange exchange) {
        ServerHttpRequest request = exchange.getRequest();
        log.error("Error no controlado del sistema en [{} {}]: ", request.getMethod(), request.getURI().getPath(), ex);

        ErrorResponse errorResponse = new ErrorResponse(
                "INTERNAL_SERVER_ERROR",
                "Ha ocurrido un error interno inesperado en la aplicación.",
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                request.getURI().getPath(),
                LocalDateTime.now()
        );

        return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse));
    }
}
