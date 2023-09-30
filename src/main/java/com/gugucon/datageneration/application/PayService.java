package com.gugucon.datageneration.application;

import com.gugucon.datageneration.domain.Order;
import com.gugucon.datageneration.generator.PayGenerator;
import com.gugucon.datageneration.repository.OrderRepository;
import com.gugucon.datageneration.repository.PayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PayService {

    private final PayRepository payRepository;
    private final PayGenerator payGenerator;
    private final OrderRepository orderRepository;

    public long createPay() {
        List<Long> payedOrderIds = orderRepository.findAllIdByStatus(Order.OrderStatus.COMPLETED);

        payedOrderIds.stream()
                .parallel()
                .map(payGenerator::generate)
                .forEach(payRepository::save);

        return payRepository.count();
    }
}
