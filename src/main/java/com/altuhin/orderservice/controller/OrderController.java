package com.altuhin.orderservice.controller;


import com.altuhin.orderservice.dto.OrderDto;
import com.altuhin.orderservice.repository.repository.OrderLineItemsRepository;
import com.altuhin.orderservice.service.OrderService;
import com.altuhin.orderservice.unit.ApiResponse;
import com.altuhin.orderservice.unit.ApiResponseEntityFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {
    
    private final OrderService orderService;
    private final OrderLineItemsRepository orderLineItemsRepository;
    private final ApiResponseEntityFactory responseFactory;
    
    @PostMapping()
    public ResponseEntity<ApiResponse<OrderDto>> placeOrder(@RequestBody OrderDto orderDto) {
        return responseFactory.saveResponse(orderService.placeOrder(orderDto));
    }
    
    @PutMapping()
    public ResponseEntity<ApiResponse<OrderDto>> update(@RequestBody OrderDto orderDto) {
        return responseFactory.updateResponse("Successfully Updated",
                orderService.update(orderDto));
    }

//    @PostMapping("/search")
//    public ResponseEntity<ApiResponse<Page<OrderDto>>> search(@RequestBody OrderSearchDto
//                                                                      productSearchDto) {
//        return responseFactory.getResponse(orderService.search(productSearchDto));
//
//    }
}
