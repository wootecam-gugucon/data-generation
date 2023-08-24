package com.gugucon.datageneration.application;

import com.gugucon.datageneration.domain.Pay;
import com.gugucon.datageneration.generator.PayGenerator;
import com.gugucon.datageneration.repository.PayRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PayService {

    private final PayRepository payRepository;
    private final PayGenerator payGenerator;

    public int createPay(final List<Long> orderIds) {
        List<Pay> pays = payGenerator.generate(orderIds);
        pays.stream()
            .parallel()
            .forEach(payRepository::save);
        return pays.size();
    }
}
