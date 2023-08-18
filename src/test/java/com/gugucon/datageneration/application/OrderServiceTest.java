package com.gugucon.datageneration.application;

import static org.assertj.core.api.Assertions.assertThat;

import com.gugucon.datageneration.domain.Member;
import com.gugucon.datageneration.domain.Product;
import com.gugucon.datageneration.repository.MemberRepository;
import com.gugucon.datageneration.repository.OrderItemRepository;
import com.gugucon.datageneration.repository.OrderRepository;
import com.gugucon.datageneration.repository.ProductRepository;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ProductRepository productRepository;

    @Test
    void createOrderItems() {
        // given
        orderItemRepository.deleteAllInBatch();
        orderRepository.deleteAllInBatch();
        productRepository.deleteAllInBatch();
        memberRepository.deleteAllInBatch();

        productService.createProduct(10000);
        List<Product> products = productRepository.findAll();

        memberService.createMember(1000);
        List<Long> memberIds = memberRepository.findAll()
                                               .stream()
                                               .map(Member::getId)
                                               .toList();

        // when
        int orderCount = 10_000;
        int count = orderService.createOrder(memberIds, products, orderCount);

        // then
        assertThat(count).isEqualTo(orderCount);
    }

    @Test
    void deleteAll() {
        // when
        orderItemRepository.deleteAllInBatch();
        orderRepository.deleteAllInBatch();

        // then
        assertThat(orderRepository.count()).isZero();
        assertThat(orderItemRepository.count()).isZero();
    }

}