package com.gugucon.datageneration.application;

import static org.assertj.core.api.Assertions.assertThat;

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
    void createProduct() {
        // given
        productRepository.deleteAll();

        // when
        int count = productService.createProduct(10000);

        // then
        assertThat(productRepository.count()).isEqualTo(count);
    }

    @Test
    void deleteData() {
        // when
        productRepository.deleteAll();

        // then
        assertThat(productRepository.count()).isZero();
    }
}
