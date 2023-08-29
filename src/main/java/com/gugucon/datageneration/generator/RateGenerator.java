package com.gugucon.datageneration.generator;

import com.gugucon.datageneration.domain.Rate;
import java.util.Optional;
import java.util.Random;

public class RateGenerator {

    private static final Random random = new Random();

    public Optional<Rate> generate(final Long orderItemId) {

        if (random.nextBoolean()) {
            return Optional.empty();
        }

        return Optional.ofNullable(Rate.builder()
                                           .orderItemId(orderItemId)
                                           .score((short) random.nextInt(1, 6))
                                           .build());
    }
}
