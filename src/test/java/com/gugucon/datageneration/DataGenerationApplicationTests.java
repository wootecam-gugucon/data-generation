package com.gugucon.datageneration;

import static org.assertj.core.api.Assertions.assertThat;

import com.gugucon.datageneration.application.CartItemService;
import com.gugucon.datageneration.application.MemberService;
import com.gugucon.datageneration.application.OrderService;
import com.gugucon.datageneration.application.PayService;
import com.gugucon.datageneration.application.ProductService;
import com.gugucon.datageneration.domain.Member;
import com.gugucon.datageneration.domain.Product;
import com.gugucon.datageneration.domain.Status;
import com.gugucon.datageneration.repository.CartItemRepository;
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
class DataGenerationApplicationTests {

    @Autowired
    private ProductService productService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private PayService payService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private PayRepository payRepository;

    @Test
    void createAll() {
        // given
        payRepository.deleteAllInBatch();
        orderItemRepository.deleteAllInBatch();
        cartItemRepository.deleteAllInBatch();
        orderRepository.deleteAllInBatch();
        productRepository.deleteAllInBatch();
        memberRepository.deleteAllInBatch();

        int productCount = 10_000;
        int memberCount = 1_000;
        int cartItemCount = memberCount * 3;
        int orderCount = 1_000_000;

        // when
        productService.createProduct(productCount);
        List<Product> products = productRepository.findAll();
        List<Long> productIds = products.stream()
                                        .map(Product::getId)
                                        .toList();

        memberService.createMember(memberCount);
        List<Long> memberIds = memberRepository.findAll()
                                               .stream()
                                               .map(Member::getId)
                                               .toList();

        cartItemService.createCartItems(memberIds, productIds, cartItemCount);

        orderService.createOrder(memberIds, products, orderCount);
        List<Long> payedOrderIds = orderRepository.findAllIdByStatus(Status.COMPLETED);

        int payCount = payService.createPay(payedOrderIds);

        // then
        assertThat(productRepository.count()).isEqualTo(productCount);
        assertThat(memberRepository.count()).isEqualTo(memberCount);
        assertThat(cartItemRepository.count()).isEqualTo(cartItemCount);
        assertThat(orderRepository.count()).isEqualTo(orderCount);
        assertThat(payRepository.count()).isEqualTo(payCount);
    }
}
