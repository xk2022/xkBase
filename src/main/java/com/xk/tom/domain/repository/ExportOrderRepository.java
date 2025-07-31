package com.xk.tom.domain.repository;

import com.xk.tom.domain.model.aggreate.ExportOrderAggreate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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


    @Query(value = """
    SELECT * FROM export_order o
        WHERE (
                o.vessel_voyage LIKE CONCAT('%', :keyword, '%') OR
                o.shipping_company LIKE CONCAT('%', :keyword, '%') OR
                o.pickup_yard LIKE CONCAT('%', :keyword, '%') OR
                o.pickup_code LIKE CONCAT('%', :keyword, '%') OR
                o.note LIKE CONCAT('%', :keyword, '%') OR
                o.loading_location LIKE CONCAT('%', :keyword, '%') OR
                o.delivery_yard LIKE CONCAT('%', :keyword, '%') OR
                o.container_type  LIKE CONCAT('%', :keyword, '%') OR
                o.container_number LIKE CONCAT('%', :keyword, '%') OR
                o.update_by LIKE CONCAT('%', :keyword, '%') OR
                o.status LIKE CONCAT('%', :keyword, '%') OR
                o.created_by LIKE CONCAT('%', :keyword, '%') OR
            )AND o.order_type='EXPORT';
   \s""", nativeQuery = true)
    List<ExportOrderAggreate> findByExportOrderByKeyWord(@Param("keyword") String keyword);
}
