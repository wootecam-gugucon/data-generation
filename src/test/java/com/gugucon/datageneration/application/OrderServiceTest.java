package com.gugucon.datageneration.application;

import static org.assertj.core.api.Assertions.assertThat;

import com.gugucon.datageneration.domain.Member;
import com.gugucon.datageneration.domain.Product;
import com.gugucon.datageneration.domain.Status;
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
//        orderItemRepository.deleteAllInBatch();
//        orderRepository.deleteAllInBatch();
//        productRepository.deleteAllInBatch();
//        memberRepository.deleteAllInBatch();

//        productService.createProduct(10000);
        List<Product> products = productRepository.findAll();

//        memberService.createMember(1000);
//        List<Long> memberIds = memberRepository.findAll()
//                                               .stream()
//                                               .map(Member::getId)
//                                               .toList();

        // when
        // 250만 249만 / 1만
        Status[] statuses1 = new Status[] {Status.CREATED, Status.PAYING};
        Status[] statuses2 = new Status[] {Status.COMPLETED, Status.CANCELED};
//        long count = orderService.createOrder(memberIds, 10_000, statuses1);
        // 0 1_000_000
        // 1_000_001 2_000_000
        System.out.println("before orderItemRepository = " + orderItemRepository.count());
        orderService.createOrderItem(products, 4_000_001, 5_000_000);
        System.out.println("after orderItemRepository = " + orderItemRepository.count());

        // then
//        assertThat(count).isEqualTo(orderCount);
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
