package com.altuhin.orderservice.service;

import com.altuhin.orderservice.dto.OrderDto;
import com.altuhin.orderservice.entity.Order;
import com.altuhin.orderservice.repository.OrderRepository;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import static com.altuhin.orderservice.unit.TransformUtil.copyProp;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class OrderService {
    
    private final OrderRepository orderRepository;
    private final EntityManager entityManager;
    
    @Transactional
    public OrderDto save(OrderDto orderDto) {
        Order order = orderRepository.save(copyProp(orderDto, Order.class));
        return copyProp(orderRepository.save(order), OrderDto.class);
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
