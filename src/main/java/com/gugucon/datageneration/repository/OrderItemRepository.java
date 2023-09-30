package com.gugucon.datageneration.repository;

import com.gugucon.datageneration.domain.Order;
import com.gugucon.datageneration.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    @Query("select sum(o.price) from OrderItem o where o.orderId = :orderId")
    Long sumPriceByOrderId(Long orderId);

    @Query("select oi.id from OrderItem oi " +
            "inner join Order o on oi.orderId = o.id " +
            "where o.status = :status")
    List<Long> findAllIdByOrderStatus(@Param("status")Order.OrderStatus status);
}
