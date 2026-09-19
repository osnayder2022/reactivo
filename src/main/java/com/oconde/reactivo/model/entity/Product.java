package com.oconde.reactivo.model.entity;

import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.annotation.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table("product")
public class Product {

    @Id
    private Long id;
    private String name;
    private String description;
    private Double price;


    @CreatedBy
    @Column("created_by")
    private String createdBy;

    @LastModifiedBy
    @Column("last_modified_by")
    private String lastModifiedBy;

    @CreatedDate
    @Column("date_creation")
    private LocalDateTime dateCreation;

    @LastModifiedDate
    @Column("date_update")
    private LocalDateTime dateUpdate;
}