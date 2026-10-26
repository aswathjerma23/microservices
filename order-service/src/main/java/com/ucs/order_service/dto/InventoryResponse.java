package com.ucs.order_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder 
@Data 
@AllArgsConstructor 
@NoArgsConstructor 
@Getter 
@Setter 
public class InventoryResponse {
    private  String skuCode;
    private Boolean isInStock;
}
