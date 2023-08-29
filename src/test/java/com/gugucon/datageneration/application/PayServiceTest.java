package com.gugucon.datageneration.application;

import com.gugucon.datageneration.domain.Member;
import com.gugucon.datageneration.domain.Product;
import com.gugucon.datageneration.domain.Status;
import com.gugucon.datageneration.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PayServiceTest {

    @Autowired
    private PayService payService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private ProductService productService;

    @Autowired
    private PayRepository payRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ProductRepository productRepository;

    @Test
    void createPay() {
        // given
//        payRepository.deleteAllInBatch();
//        orderItemRepository.deleteAllInBatch();
//        orderRepository.deleteAllInBatch();
//        productRepository.deleteAllInBatch();
//        memberRepository.deleteAllInBatch();

//        productService.createProduct(10000);
//        List<Product> products = productRepository.findAll();
//
//        memberService.createMember(1000);
//        List<Long> memberIds = memberRepository.findAll()
//                                               .stream()
//                                               .map(Member::getId)
//                                               .toList();
//
//        orderService.createOrder(memberIds, 1000, Status.values());
        List<Long> payedOrderIds = orderRepository.findAllIdByStatus(Status.COMPLETED);  // 250만개

        // when
        int count = payService.createPay(payedOrderIds);

        // then
        assertThat(payRepository.count()).isEqualTo(count);
    }

    @Test
    void deleteAll() {
        // when
        payRepository.deleteAllInBatch();

        // then
        assertThat(payRepository.count()).isZero();
    }
}
