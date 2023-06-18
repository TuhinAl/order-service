package com.altuhin.orderservice.unit;

import com.altuhin.orderservice.entity.Order;
import com.altuhin.orderservice.entity.QOrder;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

@Service
@RequiredArgsConstructor
public class OrderQueryService {
    
    private final EntityManager entityManager;
    
    public Order getFirstOrderByOrderId(String orderId) {
        synchronized (this) {
            final QOrder qOrder = QOrder.order;
            final JPAQuery<Order> query = new JPAQuery<>(entityManager);
            return query.from(qOrder)
                    .where(qOrder.orderId.like("%" + orderId + "%"))
                    .orderBy(qOrder.orderId.desc())
                    .fetchFirst();
        }
    }
}
