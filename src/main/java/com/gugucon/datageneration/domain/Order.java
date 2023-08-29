package com.gugucon.datageneration.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.BatchSize;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class Order extends BaseTimeEntity {

    private static final long MAX_TOTAL_PRICE = 100_000_000_000L;
    private static final int SINGLE_ITEM_VALUE = 1;
    private static final String MULTIPLE_ITEM_EXPRESSION = " 외 %d건";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "member_id")
    private Long memberId;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Enumerated(EnumType.STRING)
    private PayType payType;


    public void startPay(final PayType type) {
        validateCreated();
        this.status = OrderStatus.PAYING;
        this.payType = type;
    }

    private void validateCreated() {
        if (status != OrderStatus.CREATED) {
            throw new RuntimeException("");
        }
    }

    public enum OrderStatus {CREATED, PAYING, COMPLETED, CANCELED}
}
