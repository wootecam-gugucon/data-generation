package com.gugucon.datageneration.application;

import static org.assertj.core.api.Assertions.assertThat;

import com.gugucon.datageneration.domain.Member;
import com.gugucon.datageneration.domain.Product;
import com.gugucon.datageneration.repository.MemberRepository;
import com.gugucon.datageneration.repository.OrderItemRepository;
import com.gugucon.datageneration.repository.OrderRepository;
import com.gugucon.datageneration.repository.PayRepository;
import com.gugucon.datageneration.repository.ProductRepository;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
        payRepository.deleteAllInBatch();
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

        orderService.createOrder(memberIds, products, 1000);
        List<Long> payedOrderIds = orderRepository.findAllIdByStatus("PAYED");

        List<Long> totalPrices = payedOrderIds.stream()
                                              .map(orderItemRepository::sumPriceByOrderId)
                                              .toList();

        // when
        int count = payService.createPay(payedOrderIds, totalPrices);

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