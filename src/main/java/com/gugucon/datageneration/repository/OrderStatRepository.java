package com.gugucon.datageneration.repository;

import com.gugucon.datageneration.domain.Gender;
import com.gugucon.datageneration.domain.OrderStat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderStatRepository extends JpaRepository<OrderStat, Long> {

    @Query("SELECT new com.gugucon.datageneration.repository.SimpleOrderStatDto(p.id, sum(oi.quantity)) " +
            "FROM Order o " +
            "JOIN OrderItem oi ON o.id = oi.orderId " +
            "JOIN Member m ON o.memberId = m.id " +
            "JOIN Product p ON p.id = oi.productId " +
            "WHERE m.birthDate between :startDate AND :endDate " +
            "AND m.gender = :gender " +
            "GROUP BY p.id")
    List<SimpleOrderStatDto> findAllSimpleOrderStatByGenderAndBirthDateBetween(@Param("gender") final Gender gender,
                                                                               @Param("startDate") final LocalDate startDate,
                                                                               @Param("endDate") final LocalDate endDate);
}
