package com.oconde.reactivo.model.dto;

import java.time.LocalDateTime;

// DTO estandarizado para devolver detalles del error al cliente
public record ErrorResponse(
        String code,
        String message,
        int status,
        String path,
        LocalDateTime timestamp
) {}