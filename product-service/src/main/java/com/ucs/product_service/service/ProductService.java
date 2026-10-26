package com.ucs.product_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ucs.product_service.dto.ProductRequest;
import com.ucs.product_service.dto.ProductResponse;
import com.ucs.product_service.model.Product;
import com.ucs.product_service.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository repository;

    public void createProduct(ProductRequest request) {
        Product product = Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .build();

        repository.save(product);
        log.info("New product has been created for the id :" + product.getId());
    }

    public List<ProductResponse> getAllProducts() {
        List<Product> products = repository.findAll();
        return products.stream().map(this::mapToProductResponse).toList();

    }

    public ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .description(product.getDescription())
                .name(product.getName())
                .price(product.getPrice())
                .build();
    }

}
