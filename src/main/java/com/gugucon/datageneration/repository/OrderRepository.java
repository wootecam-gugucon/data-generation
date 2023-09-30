package com.gugucon.datageneration.repository;

import com.gugucon.datageneration.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("select o.id from Order o where o.status = :status")
    List<Long> findAllIdByStatus(Order.OrderStatus status);

    @Query("select o.id from Order o where o.id between :id1 and :id2")
    List<Long> findAllByIdBetween(final Long id1, final Long id2);

    @Query("select o.id from Order o")
    List<Long> findAllId();
}
