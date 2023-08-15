package com.gugucon.datageneration.application;

import com.gugucon.datageneration.infrastructure.CSVReader;
import com.gugucon.datageneration.repository.ProductRepository;
import com.gugucon.datageneration.domain.Product;
import java.util.List;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CSVReader<Product> csvReader;
    private final Logger log = LoggerFactory.getLogger(ProductService.class);

    public int createData(String path, int number) {
        List<Product> products = csvReader.readCSV(path, number);
        log.info("파싱 완료");
        productRepository.saveAll(products);
        return products.size();
    }
}
