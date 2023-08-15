package com.gugucon.datageneration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class DataGenerationApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataGenerationApplication.class, args);
    }

}
