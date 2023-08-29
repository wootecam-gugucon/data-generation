package com.gugucon.datageneration.application;

import com.gugucon.datageneration.generator.StatDataGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StatDataGenerateTest {

    @Autowired
    private StatDataGenerator statDataGenerator;

    @Test
    void create() {
        statDataGenerator.generateRateStatData();
        statDataGenerator.generateOrderStatData();
    }
}
