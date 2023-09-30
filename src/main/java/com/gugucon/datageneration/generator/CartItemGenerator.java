package com.gugucon.datageneration.generator;

import com.gugucon.datageneration.domain.CartItem;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
public class CartItemGenerator {

    private static final int MIN_QUANTITY = 1;
    private static final int MAX_QUANTITY = 10;

    private final Random random = new Random();

    public CartItem generate(final List<Long> memberIds, final List<Long> productIds) {
        int memberCount = memberIds.size();
        int productCount = productIds.size();

        return CartItem.builder()
                .memberId(memberIds.get(random.nextInt(memberCount)))
                .productId(productIds.get(random.nextInt(productCount)))
                .quantity(random.nextInt(MIN_QUANTITY, MAX_QUANTITY))
                .build();
    }
}
