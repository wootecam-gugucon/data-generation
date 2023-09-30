package com.gugucon.datageneration.application;

import com.gugucon.datageneration.domain.Member;
import com.gugucon.datageneration.domain.Product;
import com.gugucon.datageneration.generator.CartItemGenerator;
import com.gugucon.datageneration.repository.CartItemRepository;
import com.gugucon.datageneration.repository.MemberRepository;
import com.gugucon.datageneration.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class CartItemService {

    private final CartItemRepository cartItemRepository;
    private final CartItemGenerator cartItemGenerator;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;

    public long createCartItems(final int count) {
        final List<Long> memberIds = memberRepository.findAll().stream()
                .map(Member::getId)
                .toList();
        final List<Long> productIds = productRepository.findAll().stream()
                .map(Product::getId)
                .toList();

        IntStream.range(0, count)
                .parallel()
                .mapToObj(i -> cartItemGenerator.generate(memberIds, productIds))
                .forEach(cartItemRepository::save);

        return cartItemRepository.count();
    }
}
