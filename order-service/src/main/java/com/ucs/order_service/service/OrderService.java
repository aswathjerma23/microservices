package com.ucs.order_service.service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import com.ucs.order_service.dto.InventoryResponse;
import com.ucs.order_service.dto.OrderLineItemsDto;
import com.ucs.order_service.dto.OrderRequest;
import com.ucs.order_service.model.Order;
import com.ucs.order_service.model.OrderLineItems;
import com.ucs.order_service.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;

    public String placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItemsDto> orderLineItemsDto = orderRequest.getOrderLineItemsDtoLists();

        List<OrderLineItems> orderLineItems = orderLineItemsDto.stream().map(this::mapToDto).toList();
        order.setOrderLineItemsList(orderLineItems);

        List<String> skuCodes = orderLineItems.stream().map(orderLineItem -> orderLineItem.getSkuCode()).toList();

        // call inventory service
        InventoryResponse[] inventoryResponseArray = webClientBuilder.build().get()
                .uri("http://inventory-service/api/inventory",
                        uriBuilder -> uriBuilder
                                .queryParam("skuCode", skuCodes)
                                .build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();

        boolean allProductsStock = Arrays.stream(inventoryResponseArray)
                .allMatch(inventoryResponse -> inventoryResponse.getIsInStock());

        if (allProductsStock) {
            orderRepository.save(order);
            return "Order placed Successfully!";
        } else {
            throw new IllegalArgumentException("Product is not in the stock. please tryagain later");
        }

    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        return orderLineItems;
    }

}
