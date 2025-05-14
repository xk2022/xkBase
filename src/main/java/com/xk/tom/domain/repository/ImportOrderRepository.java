package com.xk.tom.domain.repository;

import com.xk.tom.domain.model.aggreate.ImportOrderAggreate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ImportOrderRepository extends JpaRepository<ImportOrderAggreate, Long> {

    @Query(value = "SELECT * FROM Import_Order o WHERE o.import_id = :importId AND o.status = 'PENDING'", nativeQuery = true)
    Optional<ImportOrderAggreate> findByImportIdAndStatusPending(@Param("importId") Long importId);

    Optional<ImportOrderAggreate> findByOrderId(String orderId);
}
