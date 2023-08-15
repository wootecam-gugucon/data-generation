package com.gugucon.datageneration.application;

import com.gugucon.datageneration.infrastructure.CSVReader;
import com.gugucon.datageneration.repository.ProductRepository;
import com.gugucon.datageneration.domain.Product;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CSVReader<Product> csvReader;

    public int createData(String path) {
        List<Product> products = csvReader.readCSV(path);
        System.out.println("파싱 완료");
        productRepository.saveAll(products);
        return products.size();
    }
}
