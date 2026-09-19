package com.oconde.reactivo.controller;

import com.oconde.reactivo.middleware.exception.BusinessException;
import com.oconde.reactivo.model.dto.ProductCreateDTO;
import com.oconde.reactivo.model.dto.ProductDto;
import com.oconde.reactivo.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Operation(summary = "Obtener todos los productos")
    @GetMapping
    public Flux<ProductDto> getAllProducts() {
        return productService.getAllProducts()
                .doOnSubscribe(subscription -> log.info("Obteniendo todos los productos"))
                .doOnComplete(() -> log.info("Todos los productos obtenidos"));
    }

    @Operation(summary = "Obtener producto por ID")
    @GetMapping("/{id}")
    public Mono<ProductDto> getProductById(@PathVariable Long id) {
        if (id <= 0) {
            return Mono.error(new BusinessException("PRODUCT_BAD_REQUEST", "El id de producto debe ser mayor que cero.", HttpStatus.BAD_REQUEST));
        }

        return Mono.just(id)
                .doOnNext(productId -> log.info("Buscando producto con ID: {}", productId))
                .flatMap(productService::getProductById)
                .doOnNext(product -> log.info("Producto encontrado"))
                .switchIfEmpty(Mono.error(new BusinessException("PRODUCT_NOT_FOUND", "Producto no encontrado con ID: " + id, HttpStatus.NOT_FOUND)));

    }

    @Operation(summary = "Crear un nuevo producto")
    @PostMapping
    public Mono<ProductDto> createProduct(@RequestBody ProductCreateDTO productCreateDTO) {

        return Mono.just(productCreateDTO)
                .doOnNext(dto -> log.info("Creando producto: {}", dto))
                .flatMap(productService::createProduct)
                .doOnNext(createdProduct -> log.info("Producto creado con ID: {}", createdProduct.getId()));
    }

    @Operation(summary = "Actualizar un producto existente")
    @PutMapping("/{id}")
    public Mono<ProductDto> updateProduct(@PathVariable Long id, @RequestBody ProductDto productDto) {

        return Mono.just(productDto)
                .doOnNext(dto -> log.info("Actualizando producto con ID: {}", id))
                .flatMap(dto -> productService.updateProduct(id, dto))
                .doOnNext(updatedProduct -> log.info("Producto actualizado con ID: {}", updatedProduct.getId()));
    }

    @Operation(summary = "Eliminar un producto por ID")
    @DeleteMapping("/{id}")
    public Mono<Void> deleteProduct(@PathVariable Long id) {

        return Mono.just(id)
                .doOnNext(productId -> log.info("Eliminando producto con ID: {}", productId))
                .flatMap(productService::deleteProduct)
                .doOnSuccess(unused -> log.info("Producto eliminado con ID: {}", id));
    }
}
