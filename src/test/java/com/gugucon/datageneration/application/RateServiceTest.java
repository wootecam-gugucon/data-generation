package com.gugucon.datageneration.application;

import static org.assertj.core.api.Assertions.assertThat;

import com.gugucon.datageneration.repository.OrderItemRepository;
import com.gugucon.datageneration.repository.RateRepository;
import java.util.stream.LongStream;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RateServiceTest {

    @Autowired
    private RateRepository rateRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private RateService rateService;

    @Test
    void createRate() {
        long count = orderItemRepository.count();
        int size = rateService.createRate(LongStream.rangeClosed(1, count).boxed().toList());

        assertThat(size).isEqualTo(rateRepository.count());
    }
}
