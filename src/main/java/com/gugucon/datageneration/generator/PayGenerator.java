package com.gugucon.datageneration.generator;

import com.gugucon.datageneration.domain.Pay;

import java.util.List;

public class PayGenerator {

    public List<Pay> generate(final List<Long> orderIds) {
        return orderIds.stream()
                .map(orderId -> Pay.builder()
                        .orderId(orderId)
                        .build())
                .toList();
    }
}
