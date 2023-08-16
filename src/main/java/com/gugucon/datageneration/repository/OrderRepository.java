package com.gugucon.datageneration.repository;

import com.gugucon.datageneration.domain.Order;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByStatus(String status);
}
