package com.gugucon.datageneration.repository;

import com.gugucon.datageneration.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    @Query("select sum(o.price) from OrderItem o where o.orderId = :orderId")
    Long sumPriceByOrderId(Long orderId);
}
