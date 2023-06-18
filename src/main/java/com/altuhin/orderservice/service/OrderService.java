package com.altuhin.orderservice.service;

import com.altuhin.orderservice.dto.OrderDto;
import com.altuhin.orderservice.entity.Order;
import com.altuhin.orderservice.entity.OrderLineItems;
import com.altuhin.orderservice.repository.OrderRepository;
import com.altuhin.orderservice.repository.OrderLineItemsRepository;
import com.altuhin.orderservice.unit.OrderNumberGeneratorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

import static com.altuhin.orderservice.unit.TransformUtil.copyProp;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class OrderService {
    
    private final OrderRepository orderRepository;
    private final OrderLineItemsRepository orderLineItemsRepository;
    private final OrderNumberGeneratorService orderNumberGeneratorService;
    private final EntityManager entityManager;
    
    @Transactional
    public OrderDto placeOrder(OrderDto orderDto) {
        Order order = copyProp(orderDto, Order.class);
        if (StringUtils.isEmpty(order.getOrderId())) {
            order.setOrderId(orderNumberGeneratorService.getGeneratedOrderNcId());
        }
        Order persistedOrder = orderRepository.save(order);
        List<OrderLineItems> orderLineItems = orderDto.getOrderLineItemsDtoList()
                .stream()
                .map(orderLineItemsDto -> {
                    return orderLineItemsRepository.save(new OrderLineItems()
                            .setPrice(orderLineItemsDto.getPrice())
                            .setOrder(persistedOrder)
                            .setQuantity(orderLineItemsDto.getQuantity()));
                }).toList();
        return copyProp(persistedOrder, OrderDto.class);
    }
    
    @Transactional
    public OrderDto update(OrderDto orderDto) {
        
        Order order = orderRepository.findById(orderDto.getId())
                .orElseThrow(
                        () -> new EntityNotFoundException("Category With This Id is not Found!")
                );
        
        return copyProp(orderRepository.save(order), OrderDto.class);
    }

//    public Page<OrderDto> search(CategorySearchDto categorySearchDto) {
//        final QCategory qCategory = QCategory.category;
//        final JPAQuery<Category> categoryQuery = new JPAQuery<>(entityManager);
//
//        Predicate predicate = CategoryPredicate.search(categorySearchDto);
//        Pageable pageable = PageRequest.of(categorySearchDto.getPage(),
//                categorySearchDto.getSize());
//
//        var query = categoryQuery
//                .from(qCategory)
//                .where(predicate)
//                .limit(pageable.getPageSize())
//                .offset(pageable.getOffset())
//                .orderBy(qCategory.createdDate.desc());
//
//        return new PageImpl<>(copyList(query.fetch(), OrderDto.class), pageable, query.fetchCount());
//    }
}
