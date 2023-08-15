package com.gugucon.datageneration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ParserConfiguration {

    @Bean
    public CSVReader<Product> productCSVReader() {
        return new CSVReader<>(new ProductParser());
    }
}
