package com.xk.tom.domain.repository;

import com.xk.tom.domain.model.aggreate.ExportOrderAggreate;

import com.xk.tom.domain.model.aggreate.OrderId;
import com.xk.tom.domain.model.bo.ExportOrderBO;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ExportOrderRepository extends JpaRepository<ExportOrderAggreate, Long> {

    @Query(name = "SELECT * FROM Export_Order o WHERE o.exportId = :exportId" , nativeQuery = true)
    Optional<ExportOrderAggreate> findByExportId(@Param("exportId") Long exportId);

    Optional<ExportOrderAggreate> findByOrderId(String orderid);

    @Query(value = "SELECT * FROM Export_Order o WHERE o.export_id = :exportId AND o.status = 'PENDING'", nativeQuery = true)
    Optional<ExportOrderAggreate> findByImportIdAndStatusPending(@Param("exportId") Long exportId);

    @Query(value = "SELECT * FROM  Export_Order o  WHERE o.order_type ='EXPORT' ",nativeQuery = true)
    List<ExportOrderAggreate> findByOrderTypeExport();

    @Query(value="SELECT * FROM export_order o Left JOIN customer c on o.customer_id = c.customer_id  where c.customer_name =:customerName \" +\n" +
            "            \" And o.order_type ='EXPORT'", nativeQuery = true)
    List<ExportOrderAggreate> findByCustomerNameAndOrderTypeExport(String customerName);

    @Query
    void update(ExportOrderAggreate exportOrderAggreate);
}
