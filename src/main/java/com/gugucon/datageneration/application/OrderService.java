package com.gugucon.datageneration.application;

import com.gugucon.datageneration.domain.Member;
import com.gugucon.datageneration.domain.Order.OrderStatus;
import com.gugucon.datageneration.domain.Product;
import com.gugucon.datageneration.generator.OrderGenerator;
import com.gugucon.datageneration.repository.MemberRepository;
import com.gugucon.datageneration.repository.OrderItemRepository;
import com.gugucon.datageneration.repository.OrderRepository;
import com.gugucon.datageneration.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderGenerator orderGenerator;
    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;

    public long createOrder(final int count, final OrderStatus... statuses) {
        List<Long> memberIds = memberRepository.findAll()
                .stream()
                .map(Member::getId)
                .toList();

        IntStream.range(0, count)
                .parallel()
                .mapToObj(i -> orderGenerator.generateOrder(memberIds, statuses))
                .forEach(orderRepository::save);

        return orderRepository.count();
    }

    public long createOrderItem() {
        List<Long> orderIds = orderRepository.findAllId();
        List<Product> products = productRepository.findAll();

        orderIds.stream()
                .parallel()
                .forEach(orderId -> orderGenerator.generateOrderItem(orderId, products)
                        .stream()
                        .forEach(orderItemRepository::save));

        return orderItemRepository.count();
    }

    public void createOrderItem(final List<Product> products, long start, long end) {
        List<Long> orderIds = orderRepository.findAllByIdBetween(start, end);
        orderIds.stream()
                .parallel()
                .forEach(orderId -> orderGenerator.generateOrderItem(orderId, products)
                        .stream()
                        .forEach(orderItemRepository::save));
    }
}
