package com.ucs.order_service.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "t_order_line_items")
@NoArgsConstructor 
@AllArgsConstructor 
@Getter 
@Setter 
public class OrderLineItems {

    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private  String skuCode;
    private BigDecimal price;
    private Integer quantity;
}
