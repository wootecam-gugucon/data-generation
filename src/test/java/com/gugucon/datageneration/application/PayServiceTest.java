package com.gugucon.datageneration.application;

import static org.assertj.core.api.Assertions.assertThat;

import com.gugucon.datageneration.domain.Member;
import com.gugucon.datageneration.domain.Order;
import com.gugucon.datageneration.domain.OrderItem;
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
    void createCartItems() {
        // given
        payRepository.deleteAll();
        orderRepository.deleteAll();
        orderItemRepository.deleteAll();
        productRepository.deleteAll();
        memberRepository.deleteAll();

        String path = "/Users/woowatech1/Downloads/train.csv";
        productService.createData(path, 10000);
        List<Product> products = productRepository.findAll();

        memberService.createMember(1000);
        List<Long> memberIds = memberRepository.findAll()
                                               .stream()
                                               .map(Member::getId)
                                               .toList();

        orderService.createOrder(memberIds, products, 1000);
        List<Long> payedOrderIds = orderRepository.findAllByStatus("PAYED")
                                                  .stream()
                                                  .map(Order::getId)
                                                  .toList();

        List<Long> totalPrices = payedOrderIds.stream()
                                              .map(id -> orderItemRepository.findAllByOrderId(id)
                                                                            .stream()
                                                                            .mapToLong(OrderItem::getPrice)
                                                                            .sum())
                                              .toList();

        // when
        int count = payService.createPay(payedOrderIds, totalPrices);

        // then
        assertThat(payRepository.count()).isEqualTo(count);
    }

    @Test
    void deleteAll() {
        // when
        payRepository.deleteAll();

        // then
        assertThat(payRepository.count()).isZero();
    }
}