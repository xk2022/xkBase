package com.xk.tom.domain.repository;

import com.xk.tom.domain.model.aggreate.OrderRecordAggreate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRecordRepository extends JpaRepository<OrderRecordAggreate, Long> {
}
