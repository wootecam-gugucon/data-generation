package com.gugucon.datageneration.repository;

import com.gugucon.datageneration.domain.Pay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayRepository extends JpaRepository<Pay, Long> {
}
