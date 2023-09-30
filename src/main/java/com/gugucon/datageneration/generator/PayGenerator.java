package com.gugucon.datageneration.generator;

import com.gugucon.datageneration.domain.Pay;
import org.springframework.stereotype.Component;

@Component
public class PayGenerator {

    public Pay generate(final Long orderId) {
        return Pay.builder()
                .orderId(orderId)
                .build();
    }
}
