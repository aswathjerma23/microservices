package com.ucs.product_service.model;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

@Document(collection = "products")
@Builder
@Data
public class Product {
    @Id 
    private String id;
    private String name;
    private String description;
    private BigDecimal price;
}
