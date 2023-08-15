package com.gugucon.datageneration.application;

import static org.assertj.core.api.Assertions.assertThat;

import com.gugucon.datageneration.domain.Member;
import com.gugucon.datageneration.domain.Product;
import com.gugucon.datageneration.repository.CartItemRepository;
import com.gugucon.datageneration.repository.MemberRepository;
import com.gugucon.datageneration.repository.ProductRepository;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
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
    void createCartItem() {
        // given
        cartItemRepository.deleteAll();

        productRepository.deleteAll();
        String path = "/Users/woowatech1/Downloads/train.csv";
        int productCount = productService.createData(path, 10000);
        List<Long> productIds = productRepository.findAll()
                                                 .stream()
                                                 .map(Product::getId)
                                                 .toList();

        memberRepository.deleteAll();
        memberService.createMember(1000);
        List<Long> memberIds = memberRepository.findAll()
                                               .stream()
                                               .map(Member::getId)
                                               .toList();

        Random random = new Random();

        // when
        int count = memberIds.stream()
                             .mapToInt(memberId ->
                                               IntStream.range(0, 5)
                                                        .map(i -> {
                                                            Long productId = productIds.get(random.nextInt(productCount));
                                                            return cartItemService.createCartItem(memberId, productId);
                                                        })
                                                        .sum())
                             .sum();

        // then
        assertThat(cartItemRepository.count()).isEqualTo(count);
    }

    @Test
    void deleteAll() {
        // when
        cartItemRepository.deleteAll();

        // then
        assertThat(cartItemRepository.count()).isZero();
    }
}