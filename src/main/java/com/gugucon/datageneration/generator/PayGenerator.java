package com.gugucon.datageneration.generator;

import com.gugucon.datageneration.domain.Pay;
import java.util.List;
import java.util.stream.IntStream;

public class PayGenerator {

    public List<Pay> generate(final List<Long> orderIds, final List<Long> totalPrices) {
        if (orderIds.size() != totalPrices.size()) {
            throw new IllegalStateException("결제된 주문 개수와 주문 금액 총합 리스트 개수가 다릅니다. id: " + orderIds.size() + ", 금액: " + totalPrices.size());
        }

        return IntStream.range(0, orderIds.size())
                        .mapToObj(i -> Pay.builder()
                                          .orderId(orderIds.get(i))
                                          .price(totalPrices.get(i))
                                          .build())
                        .toList();
    }
}
