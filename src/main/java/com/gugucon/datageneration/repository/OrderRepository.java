package com.gugucon.datageneration.repository;

import com.gugucon.datageneration.domain.Order;
import java.util.List;

import com.gugucon.datageneration.domain.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("select o.id from Order o where o.status = :status")
    List<Long> findAllIdByStatus(Status status);

    @Query("select o.id from Order o")
    List<Long> findAllId();
}
