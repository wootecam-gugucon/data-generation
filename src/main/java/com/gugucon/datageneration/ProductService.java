package com.gugucon.datageneration;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CSVReader<Product> csvReader;

    public ProductService(final ProductRepository productRepository, final CSVReader<Product> csvReader) {
        this.productRepository = productRepository;
        this.csvReader = csvReader;
    }

    public int createData(String path) {
        List<Product> products = csvReader.readCSV(path);
        System.out.println("파싱 완료");
        products.stream().forEach(productRepository::save);
        return products.size();
    }
}
