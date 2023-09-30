package com.gugucon.datageneration.generator;

import com.gugucon.datageneration.domain.Rate;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RateGenerator {

    private final Random random = new Random();

    public Rate generate(final Long orderItemId) {

        return Rate.builder()
                .orderItemId(orderItemId)
                .score((short) random.nextInt(1, 6))
                .build();
    }
}
