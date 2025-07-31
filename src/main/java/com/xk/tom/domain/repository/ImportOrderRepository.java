package com.xk.tom.domain.repository;

import com.xk.tom.application.model.OrderResponseDTO;
import com.xk.tom.domain.model.aggreate.ImportOrderAggreate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ImportOrderRepository extends JpaRepository<ImportOrderAggreate, Long> {

    @Query(value = "SELECT * FROM Import_Order o WHERE o.import_id = :importId AND o.status = 'PENDING'", nativeQuery = true)
    Optional<ImportOrderAggreate> findByImportIdAndStatusPending(@Param("importId") Long importId);

    Optional<ImportOrderAggreate> findByOrderId(String orderId);

    @Query(value = "SELECT * FROM  Import_Order o  WHERE o.order_type ='IMPORT' ", nativeQuery = true)
    List<ImportOrderAggreate> findByOrderTypeImport();

    @Query(value = "SELECT * FROM import_order o Left JOIN customer c on o.customer_id = c.customer_id  where c.customer_name =:customerName " +
            " And o.order_type ='IMPORT'  ", nativeQuery = true)
    List<ImportOrderAggreate> findByCustomerNameAndOrderTypeImport(String customerName);


    @Query(value = """
            SELECT * FROM import_order o
            WHERE (
                :keyword IS NULL OR (
                    o.delivery_order_location LIKE CONCAT('%', :keyword, '%') OR
                    o.shipping_company LIKE CONCAT('%', :keyword, '%') OR
                    o.vessel_voyage LIKE CONCAT('%', :keyword, '%') OR
                    o.container_number LIKE CONCAT('%', :keyword, '%') OR
                    o.container_type LIKE CONCAT('%', :keyword, '%') OR
                    o.container_yard LIKE CONCAT('%', :keyword, '%') OR
                    o.delivery_location LIKE CONCAT('%', :keyword, '%') OR
                    o.return_yard LIKE CONCAT('%', :keyword, '%') OR
                    o.note LIKE CONCAT('%', :keyword, '%') OR
                    o.status LIKE CONCAT('%', :keyword, '%') OR
                    o.created_by LIKE CONCAT('%', :keyword, '%') OR
                    o.update_by LIKE CONCAT('%', :keyword, '%')
                )
            )AND o.order_type = 'IMPORT';
            """, nativeQuery = true)
    List<ImportOrderAggreate> findByImportOrderByKeyWord(@Param("keyword") String keyword);


}
