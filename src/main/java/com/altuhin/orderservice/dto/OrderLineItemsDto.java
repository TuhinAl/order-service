package com.altuhin.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
public class OrderLineItemsDto {
    
    
    private String id;
    
    private String orderId;
    
    private BigDecimal price;
    
    private Integer quantity;
    
    private OrderDto orderDto;
    
    
}
