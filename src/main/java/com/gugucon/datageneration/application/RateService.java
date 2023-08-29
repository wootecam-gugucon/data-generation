package com.gugucon.datageneration.application;

import com.gugucon.datageneration.generator.RateGenerator;
import com.gugucon.datageneration.repository.RateRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RateService {

    private RateRepository rateRepository;
    private RateGenerator rateGenerator;

    public int createRate(final List<Long> orderItemIds) {
        int size = orderItemIds.size();
        orderItemIds.stream()
                .parallel()
                .forEach(id -> rateGenerator.generate(id)
                        .ifPresent(rateRepository::save));
        return size;
    }

}
