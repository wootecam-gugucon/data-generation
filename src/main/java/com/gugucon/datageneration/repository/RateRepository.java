package com.gugucon.datageneration.repository;

import com.gugucon.datageneration.domain.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RateRepository extends JpaRepository<Rate, Long> {
}
