package com.xk.tom.infrastructure.persistence.repository;

import com.xk.tom.domain.model.enums.OrderStatus;
import com.xk.tom.infrastructure.persistence.model.po.ExportOrderPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * 📌 SpringDataExportOrderJpaRepository
 * <p>
 * - 基於 Spring Data JPA 的 ExportOrder 資料存取層
 * - 對應資料表：`orders`（單表繼承，Discriminator = "export"）
 * - 專責處理 **ExportOrderPo** 的 CRUD 與條件查詢
 * <p>
 * ⚠️ 注意：
 * - 僅回傳 **PO（持久化對象）**，不包含業務邏輯
 * - 業務層應透過 Repository Adapter（例如 OrderRepositoryImpl）轉換為 Entity
 *
 * @author yuan Created on 2025/08/05.
 */
@Repository
public interface SpringDataExportOrderJpaRepository extends JpaRepository<ExportOrderPo, Long> {

    /**
     * 根據 UUID 查詢出口訂單
     */
    Optional<ExportOrderPo> findByUuid(UUID uuid);

    /**
     * 根據狀態查詢出口訂單
     */
    List<ExportOrderPo> findByStatus(OrderStatus status);

    /**
     * 根據船名/航次查詢出口訂單
     */
    List<ExportOrderPo> findByVesselVoyage(String vesselVoyage);
}
