package com.gugucon.datageneration.generator;

import com.gugucon.datageneration.domain.CartItem;
import java.util.Random;

public class CartItemGenerator {

    private static final int MIN_QUANTITY = 1;
    private static final int MAX_QUANTITY = 1000;

    private final Random random = new Random();

    public CartItem generate(final Long memberId, final Long productId) {
        int quantity = random.nextInt(MIN_QUANTITY, MAX_QUANTITY);
        return CartItem.builder()
                       .memberId(memberId)
                       .productId(productId)
                       .quantity(quantity)
                       .build();
    }
}
