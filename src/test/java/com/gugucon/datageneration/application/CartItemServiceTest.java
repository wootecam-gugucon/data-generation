package com.gugucon.datageneration.application;

import static org.assertj.core.api.Assertions.assertThat;

import com.gugucon.datageneration.domain.Member;
import com.gugucon.datageneration.domain.Product;
import com.gugucon.datageneration.repository.CartItemRepository;
import com.gugucon.datageneration.repository.MemberRepository;
import com.gugucon.datageneration.repository.ProductRepository;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CartItemServiceTest {

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private ProductService productService;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ProductRepository productRepository;

    @Test
    void createCartItems() {
        // given
        cartItemRepository.deleteAllInBatch();
        productRepository.deleteAllInBatch();
        memberRepository.deleteAllInBatch();

        productService.createProduct(10000);
        List<Long> productIds = productRepository.findAll()
                                                 .stream()
                                                 .map(Product::getId)
                                                 .toList();

        memberService.createMember(1000);
        List<Long> memberIds = memberRepository.findAll()
                                               .stream()
                                               .map(Member::getId)
                                               .toList();

        // when
        int count = cartItemService.createCartItems(memberIds, productIds, 5000);

        // then
        assertThat(cartItemRepository.count()).isEqualTo(count);
    }

    @Test
    void deleteAll() {
        // when
        cartItemRepository.deleteAllInBatch();

        // then
        assertThat(cartItemRepository.count()).isZero();
    }
}