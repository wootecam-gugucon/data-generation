package com.gugucon.datageneration.application;

import com.gugucon.datageneration.domain.Order;
import com.gugucon.datageneration.domain.OrderItem;
import com.gugucon.datageneration.domain.Product;
import com.gugucon.datageneration.generator.OrderGenerator;
import com.gugucon.datageneration.repository.OrderItemRepository;
import com.gugucon.datageneration.repository.OrderRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderGenerator orderGenerator;

    public int createOrder(final List<Long> memberIds, final List<Product> products, final int orderCount) {
        List<Order> orders = orderGenerator.generateOrder(memberIds, orderCount);
        orders.stream()
              .parallel()
              .forEach(orderRepository::save);
        List<Long> orderIds = orderRepository.findAllId();
        orderIds.stream()
                .parallel()
                .forEach(orderId -> orderGenerator.generateOrderItem(orderId, products).stream().parallel().forEach(orderItemRepository::save));
        return orderIds.size();
    }
}
