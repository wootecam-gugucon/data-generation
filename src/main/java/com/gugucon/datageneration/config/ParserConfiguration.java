package com.gugucon.datageneration.config;

import com.gugucon.datageneration.domain.Product;
import com.gugucon.datageneration.infrastructure.CSVReader;
import com.gugucon.datageneration.infrastructure.ProductParser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ParserConfiguration {

    @Bean
    public CSVReader<Product> productCSVReader() {
        return new CSVReader<>(new ProductParser());
    }
}
