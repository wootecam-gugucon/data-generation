package com.gugucon.datageneration.generator;

import com.gugucon.datageneration.domain.*;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class OrderGenerator {

    private static final int MIN_QUANTITY = 1;
    private static final int MAX_QUANTITY = 10;
    private static final int MIN_ITEM = 1;
    private static final int MAX_ITEM = 5;

    private final Random random = new Random();

    public List<Order> generateOrder(final List<Long> memberIds, final int number, final Order.OrderStatus[] status) {
        final int memberCount = memberIds.size();
        final PayType[] payTypes = PayType.values();

        return IntStream.range(0, number)
                .mapToObj(i -> Order.builder()
                        .memberId(memberIds.get(random.nextInt(memberCount)))
                        .status(status[random.nextInt(status.length)])
                        .payType(payTypes[random.nextInt(payTypes.length)])
                        .build())
                .toList();
    }

    public List<OrderItem> generateOrderItem(final Long orderId, final List<Product> products) {
        final int productCount = products.size();
        final int number = random.nextInt(MIN_ITEM, MAX_ITEM);
        return IntStream.range(0, number)
                .mapToObj(i -> {
                    Product product = products.get(random.nextInt(productCount));
                    return OrderItem.builder()
                            .orderId(orderId)
                            .productId(product.getId())
                            .name(product.getName())
                            .price(product.getPrice())
                            .imageFileName(product.getImageFileName())
                            .quantity(random.nextInt(MIN_QUANTITY, MAX_QUANTITY))
                            .build();
                })
                .toList();
    }

}
