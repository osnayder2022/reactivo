package com.oconde.reactivo.model.dto;

import lombok.Data;

@Data
public class ProductCreateDTO {
    private String name;
    private String description;
    private Double price;
}
