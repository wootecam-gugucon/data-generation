package com.gugucon.datageneration.generator;

import com.gugucon.datageneration.domain.*;
import com.gugucon.datageneration.repository.OrderStatRepository;
import com.gugucon.datageneration.repository.ProductRepository;
import com.gugucon.datageneration.repository.RateStatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class StatDataGenerator {

    private final ProductRepository productRepository;
    private final RateStatRepository rateStatRepository;
    private final OrderStatRepository orderStatRepository;

    public void generateRateStatData() {
        final Set<Long> productIds = productRepository.findAll().stream()
                .map(Product::getId)
                .collect(Collectors.toUnmodifiableSet());

        for (Gender gender : Gender.values()) {
            for (BirthYearRange birthYearRange : BirthYearRange.values()) {
                final List<RateStat> rateStats = rateStatRepository.findAllSimpleRateStatByGenderAndBirthDateBetween(
                                gender,
                                birthYearRange.getStartDate(),
                                birthYearRange.getEndDate()
                        ).stream()
                        .map(simpleRateStatDto ->
                                     RateStat.builder()
                                             .productId(simpleRateStatDto.getProductId())
                                             .birthYearRange(birthYearRange)
                                             .gender(gender)
                                             .totalScore(simpleRateStatDto.getTotalScore())
                                             .count(simpleRateStatDto.getCount())
                                             .build()
                        ).toList();
                rateStatRepository.saveAll(rateStats);

                final Set<Long> unratedProductIds = new HashSet<>(productIds);
                unratedProductIds.removeAll(rateStats.stream().map(RateStat::getProductId).collect(Collectors.toSet()));

                final List<RateStat> rateStatsOfUnratedProducts = unratedProductIds.stream().map(
                        id -> RateStat.builder()
                                .productId(id)
                                .birthYearRange(birthYearRange)
                                .gender(gender)
                                .totalScore(0L)
                                .count(0L)
                                .build()
                ).toList();
                rateStatRepository.saveAll(rateStatsOfUnratedProducts);
            }
        }
    }

    public void generateOrderStatData() {
        final Set<Long> productIds = productRepository.findAll().stream()
                .map(Product::getId)
                .collect(Collectors.toUnmodifiableSet());

        for (Gender gender : Gender.values()) {
            for (BirthYearRange birthYearRange : BirthYearRange.values()) {
                final List<OrderStat> orderStats = orderStatRepository.findAllSimpleOrderStatByGenderAndBirthDateBetween(
                                gender,
                                birthYearRange.getStartDate(),
                                birthYearRange.getEndDate()
                        ).stream()
                        .map(simpleOrderStatDto ->
                                     OrderStat.builder()
                                             .productId(simpleOrderStatDto.getProductId())
                                             .birthYearRange(birthYearRange)
                                             .gender(gender)
                                             .count(simpleOrderStatDto.getCount())
                                             .build()
                        ).toList();
                orderStatRepository.saveAll(orderStats);

                final Set<Long> unorderedProductIds = new HashSet<>(productIds);
                unorderedProductIds.removeAll(
                        orderStats.stream().map(OrderStat::getProductId).collect(Collectors.toSet()));

                final List<OrderStat> orderStatsOfUnorderedProducts = unorderedProductIds.stream().map(
                        id -> OrderStat.builder()
                                .productId(id)
                                .birthYearRange(birthYearRange)
                                .gender(gender)
                                .count(0L)
                                .build()
                ).toList();
                orderStatRepository.saveAll(orderStatsOfUnorderedProducts);
            }
        }
    }
}
