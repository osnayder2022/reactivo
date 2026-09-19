package com.oconde.reactivo.service;

import com.oconde.reactivo.repository.ProductRepository;
import com.oconde.reactivo.model.dto.ProductCreateDTO;
import com.oconde.reactivo.model.dto.ProductDto;
import com.oconde.reactivo.model.entity.Product;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public ProductService(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    public Flux<ProductDto> getAllProducts() {
        return productRepository.findAll()
                .map(product -> modelMapper.map(product, ProductDto.class));
    }

    public Mono<ProductDto> getProductById(Long id) {
        return productRepository.findById(id)
                .map(product -> modelMapper.map(product, ProductDto.class));
    }

    public Mono<ProductDto> createProduct(ProductCreateDTO productCreateDTO) {
        Product product = modelMapper.map(productCreateDTO, Product.class);
        return productRepository.save(product)
                .map(savedProduct -> modelMapper.map(savedProduct, ProductDto.class));
    }

    public Mono<Void> deleteProduct(Long id) {
        return productRepository.deleteById(id);
    }

    public Mono<ProductDto> updateProduct(Long id, ProductDto productDto) {
        return productRepository.findById(id)
                .flatMap(existingProduct -> {
                    modelMapper.map(productDto, existingProduct);
                    return productRepository.save(existingProduct);
                })
                .map(updatedProduct -> modelMapper.map(updatedProduct, ProductDto.class));
    }
}
