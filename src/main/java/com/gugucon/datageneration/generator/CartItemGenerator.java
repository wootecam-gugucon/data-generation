package com.gugucon.datageneration.generator;

import com.gugucon.datageneration.domain.CartItem;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class CartItemGenerator {

    private static final int MIN_QUANTITY = 1;
    private static final int MAX_QUANTITY = 1000;

    private final Random random = new Random();

    public List<CartItem> generate(final List<Long> memberIds, final List<Long> productIds, int number) {
        int memberCount = memberIds.size();
        int productCount = productIds.size();

        return IntStream.range(0, number)
                        .mapToObj(i -> CartItem.builder()
                                               .memberId(memberIds.get(random.nextInt(memberCount)))
                                               .productId(productIds.get(random.nextInt(productCount)))
                                               .quantity(random.nextInt(MIN_QUANTITY, MAX_QUANTITY))
                                               .build())
                        .toList();
    }
}
