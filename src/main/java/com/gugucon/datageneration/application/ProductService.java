package com.gugucon.datageneration.application;

import com.gugucon.datageneration.domain.Product;
import com.gugucon.datageneration.generator.ProductGenerator;
import com.gugucon.datageneration.repository.ProductRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductGenerator productGenerator;

    public int createProduct(final int number) {
        final List<Product> products = productGenerator.generate(number);
        products.stream()
                .parallel()
                .forEach(productRepository::save);
        return products.size();
    }
}
