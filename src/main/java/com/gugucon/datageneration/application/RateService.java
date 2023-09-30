package com.gugucon.datageneration.application;

import com.gugucon.datageneration.generator.RateGenerator;
import com.gugucon.datageneration.repository.OrderItemRepository;
import com.gugucon.datageneration.repository.RateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

import static com.gugucon.datageneration.domain.Order.OrderStatus.COMPLETED;

@Service
@RequiredArgsConstructor
public class RateService {

    private final Random random = new Random();
    private final RateRepository rateRepository;
    private final RateGenerator rateGenerator;
    private final OrderItemRepository orderItemRepository;

    public long createRate() {
        List<Long> orderItemIds = orderItemRepository.findAllIdByOrderStatus(COMPLETED);

        orderItemIds.stream()
                .parallel()
                .filter(id -> random.nextBoolean())
                .map(rateGenerator::generate)
                .forEach(rateRepository::save);

        return rateRepository.count();
    }

}
