package com.gugucon.datageneration.config;

import com.gugucon.datageneration.generator.CartItemGenerator;
import com.gugucon.datageneration.generator.MemberGenerator;
import com.gugucon.datageneration.generator.OrderGenerator;
import com.gugucon.datageneration.generator.PayGenerator;
import com.gugucon.datageneration.generator.ProductGenerator;
import com.gugucon.datageneration.generator.RateGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GeneratorConfiguration {

    @Bean
    public ProductGenerator productGenerator() {
        return new ProductGenerator();
    }

    @Bean
    public MemberGenerator memberGenerator() {
        return new MemberGenerator();
    }

    @Bean
    public CartItemGenerator cartItemGenerator() {
        return new CartItemGenerator();
    }

    @Bean
    public OrderGenerator orderGenerator() {
        return new OrderGenerator();
    }

    @Bean
    public PayGenerator payGenerator() {
        return new PayGenerator();
    }

    @Bean
    public RateGenerator rateGenerator() {
        return new RateGenerator();
    }
}
