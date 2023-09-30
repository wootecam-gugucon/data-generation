package com.gugucon.datageneration;

import com.gugucon.datageneration.application.*;
import com.gugucon.datageneration.application.StatService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.gugucon.datageneration.domain.Order.OrderStatus.COMPLETED;

@SpringBootTest
class DataGenerationApplicationTests {

    @Autowired
    private ProductService productService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private PayService payService;

    @Autowired
    private RateService rateService;

    @Autowired
    private StatService statService;

    @Test
    void createAll() {
        int productCount = 1_000;
        int memberCount = 50_000;
        int orderCount = 3_000_000;
        long start;

        // when
        start = System.currentTimeMillis();
        productService.createProduct(productCount);
        System.out.println("상품 생성 완료, 걸린 시간 : " + (double) (System.currentTimeMillis() - start) / 1000 + "s");

        start = System.currentTimeMillis();
        memberService.createMember(memberCount);
        System.out.println("회원 생성 완료, 걸린 시간 : " + (double) (System.currentTimeMillis() - start) / 1000 + "s");

        start = System.currentTimeMillis();
        orderService.createOrder(orderCount, COMPLETED);
        System.out.println("주문 생성 완료, 걸린 시간 : " + (double) (System.currentTimeMillis() - start) / 1000 + "s");

        start = System.currentTimeMillis();
        orderService.createOrderItem();
        System.out.println("주문상품 생성 완료, 걸린 시간 : " + (double) (System.currentTimeMillis() - start) / 1000 + "s");

        start = System.currentTimeMillis();
        payService.createPay();
        System.out.println("결제 생성 완료, 걸린 시간 : " + (double) (System.currentTimeMillis() - start) / 1000 + "s");

        start = System.currentTimeMillis();
        rateService.createRate();
        System.out.println("별점 생성 완료, 걸린 시간 : " + (double) (System.currentTimeMillis() - start) / 1000 + "s");

        start = System.currentTimeMillis();
        statService.createRateStat();
        System.out.println("별점 통계 생성 완료, 걸린 시간 : " + (double) (System.currentTimeMillis() - start) / 1000 + "s");

        start = System.currentTimeMillis();
        statService.createOrderStat();
        System.out.println("주문 통계 생성 완료, 걸린 시간 : " + (double) (System.currentTimeMillis() - start) / 1000 + "s");
    }
}
