package com.gugucon.datageneration.application;

import static org.assertj.core.api.Assertions.assertThat;

import com.gugucon.datageneration.application.ProductService;
import com.gugucon.datageneration.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductServiceTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @Test
    void createData() {
        productRepository.deleteAll();
        String path = "/Users/woowatech1/Downloads/train.csv";
        int count = productService.createData(path, 10000);
        assertThat(productRepository.count()).isEqualTo(count);
    }

    @Test
    void deleteData() {
        productRepository.deleteAll();
        assertThat(productRepository.count()).isZero();
    }
}
