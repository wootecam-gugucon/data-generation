package com.gugucon.datageneration.application;

import com.gugucon.datageneration.domain.CartItem;
import com.gugucon.datageneration.generator.CartItemGenerator;
import com.gugucon.datageneration.repository.CartItemRepository;
import com.gugucon.datageneration.repository.ProductRepository;
import java.util.Random;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CartItemService {

    private final CartItemRepository cartItemRepository;
    private final CartItemGenerator cartItemGenerator;
    private final ProductRepository productRepository;
    private final Random random = new Random();

    /*public int createCartItem(final Long memberId, final int productCount) {
        final int idx = random.nextInt(productCount);
        final Page<Product> productPage = productRepository.findAll(PageRequest.of(idx, 1));
        final Product product = productPage.getContent().get(0);

        final CartItem cartItem = cartItemGenerator.generate(memberId, product);
        cartItemRepository.save(cartItem);
        return 1;
    } */

    public int createCartItem(final Long memberId, final Long productId) {
        final CartItem cartItem = cartItemGenerator.generate(memberId, productId);
        cartItemRepository.save(cartItem);
        return 1;
    }
}
