package com.ucs.product_service.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data 
@AllArgsConstructor 
@NoArgsConstructor 
@Builder 
public class ProductRequest {
    private String name;
    private String description;
    private BigDecimal price;
}
