package com.gugucon.datageneration.repository;

import com.gugucon.datageneration.domain.Gender;
import com.gugucon.datageneration.domain.RateStat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface RateStatRepository extends JpaRepository<RateStat, Long> {

    @Query("SELECT new com.gugucon.datageneration.repository.SimpleRateStatDto(p.id, sum(r.score), count(r.score)) " +
            "FROM Order o " +
            "JOIN OrderItem oi ON o.id = oi.orderId " +
            "JOIN Member m ON o.memberId = m.id " +
            "JOIN Rate r ON r.orderItemId = oi.id " +
            "JOIN Product p ON p.id = oi.productId " +
            "WHERE m.birthDate between :startDate AND :endDate " +
            "AND m.gender = :gender " +
            "GROUP BY p.id")
    List<SimpleRateStatDto> findAllSimpleRateStatByGenderAndBirthDateBetween(@Param("gender") final Gender gender,
                                                                             @Param("startDate") final LocalDate startDate,
                                                                             @Param("endDate") final LocalDate endDate);
}
