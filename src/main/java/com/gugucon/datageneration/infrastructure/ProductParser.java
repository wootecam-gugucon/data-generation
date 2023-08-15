package com.gugucon.datageneration.infrastructure;

import com.gugucon.datageneration.domain.Product;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ProductParser implements Parser<Product> {

    private final Random random = new Random();

    @Override
    public Product parse(final String str) {
        List<String> strList = Arrays.asList(str.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1));

        String title = strList.get(1);
        if (title.length() > 255) {
            title = title.split("[,|;]")[0];
        }
        if (title.length() > 255) {
            title = title.substring(0, 250);
        }
        title = title.replaceAll("\\P{Print}", "");

        String description = strList.get(3);
        if (description.length() > 1000) {
            description = description.substring(0, 1000);
        }
        description = description.replaceAll("\\P{Print}", "");

        Long price = random.nextInt(100) * 1000L;
        Integer stock = random.nextInt(1000);

        return Product.builder()
                      .id(Long.parseLong(strList.get(0)))
                      .name(title)
                      .price(price)
                      .imageFileName("/default.jpg")
                      .stock(stock)
                      .description(description)
                      .build();
    }
}
