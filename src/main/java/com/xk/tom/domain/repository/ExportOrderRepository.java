package com.xk.tom.domain.repository;

import com.xk.tom.domain.model.aggreate.ExportOrderAggreate;

import com.xk.tom.domain.model.aggreate.OrderId;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.Optional;

public interface ExportOrderRepository extends JpaRepository<ExportOrderAggreate, OrderId> {

    @Query(name = "SELECT * FROM Order o WHERE o.orderId = :orderId" , nativeQuery = true)
    Optional<ExportOrderAggreate> findByOrderIdForUpdate(@Param("orderId") String orderId);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query(name = " SELECT MAX(e.orderId.sequence) FROM Export_Order e WHERE e.orderId.seqDate = :seqDate", nativeQuery = true)
    Long findMaxSequenceByDate(@Param("seqDate") Date seqDate);
}
