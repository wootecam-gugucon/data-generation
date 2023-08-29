package com.gugucon.datageneration.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "order_stats")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class OrderStat extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productId;

    @Enumerated(EnumType.STRING)
    private BirthYearRange birthYearRange;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private Long count;
}
