package com.gugucon.datageneration.application;

import com.gugucon.datageneration.domain.CartItem;
import com.gugucon.datageneration.generator.CartItemGenerator;
import com.gugucon.datageneration.repository.CartItemRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CartItemService {

    private final CartItemRepository cartItemRepository;
    private final CartItemGenerator cartItemGenerator;

    public int createCartItems(final List<Long> memberIds, final List<Long> productIds, final int number) {
        final List<CartItem> cartItems =  cartItemGenerator.generate(memberIds, productIds, number);
        cartItems.stream()
                 .parallel()
                 .forEach(cartItemRepository::save);
        return cartItems.size();
    }
}
