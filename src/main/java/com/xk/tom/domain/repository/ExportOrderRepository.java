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

public interface ExportOrderRepository extends JpaRepository<ExportOrderAggreate, Long> {

    @Query(name = "SELECT * FROM Export_Order o WHERE o.exportId = :exportId" , nativeQuery = true)
    Optional<ExportOrderAggreate> findByExportId(@Param("exportId") Long exportId);

}
