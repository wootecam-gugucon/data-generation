package com.gugucon.datageneration.config;

import com.gugucon.datageneration.generator.MemberGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GeneratorConfiguration {

    @Bean
    public MemberGenerator memberGenerator() {
        return new MemberGenerator();
    }
}
