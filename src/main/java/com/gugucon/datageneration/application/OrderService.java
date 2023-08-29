package com.gugucon.datageneration.application;

import com.gugucon.datageneration.domain.Order;
import com.gugucon.datageneration.domain.OrderItem;
import com.gugucon.datageneration.domain.Product;
import com.gugucon.datageneration.domain.Status;
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

    public long createOrder(final List<Long> memberIds, final int count, final Order.OrderStatus[] statuses) {
        List<Order> orders = orderGenerator.generateOrder(memberIds, count, statuses);
        orders.stream()
              .parallel()
              .forEach(orderRepository::save);

        return orderRepository.count();
    }

    public void createOrderItem(final List<Product> products) {
        List<Long> orderIds = orderRepository.findAllId();
        orderIds.stream()
                .parallel()
                .forEach(orderId -> orderGenerator.generateOrderItem(orderId, products).stream().parallel().forEach(orderItemRepository::save));
    }

    public void createOrderItem(final List<Product> products, long start, long end) {
        List<Long> orderIds = orderRepository.findAllByIdBetween(start, end);
        orderIds.stream()
                .parallel()
                .forEach(orderId -> orderGenerator.generateOrderItem(orderId, products).stream().parallel().forEach(orderItemRepository::save));
    }
}
