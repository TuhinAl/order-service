package com.altuhin.orderservice.unit;

import com.altuhin.orderservice.entity.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderNumberGeneratorService {
    
    private final OrderQueryService orderQueryService;
    
    public String getGeneratedOrderNcId() {
        synchronized (this) {
            LocalDate localDate = LocalDate.now();
            String year = localDate.format(DateTimeFormatter.ofPattern("yy"));
            String month = localDate.format(DateTimeFormatter.ofPattern("MM"));
            String orderId = "ORD" + "01" + year + month;
            Order order = orderQueryService.getFirstOrderByOrderId(orderId);
            String patientRegIdAuto;
            if (Optional.ofNullable(order).isPresent()) {
                patientRegIdAuto = orderId + StringUtil.joinerStringLastPartIncrement(order.getOrderNcId(), 6, 1);
            } else {
                patientRegIdAuto = orderId + StringUtil.intToZeroAddedString(1, 6);
            }
            return patientRegIdAuto;
        }
    }
}
