package com.xk.tom.infrastructure.persistence.repository;


import com.xk.tom.domain.model.enums.OrderStatus;
import com.xk.tom.infrastructure.persistence.model.po.ImportOrderPo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * 📌 SpringDataImportOrderJpaRepository
 * <p>
 * - 基於 Spring Data JPA 的 ImportOrder 資料存取層
 * - 對應資料表：`orders`（單表繼承，Discriminator = "import"）
 * - 專責處理 **ImportOrderPo** 的 CRUD 與條件查詢
 * <p>
 * ⚠️ 注意：
 * - 僅回傳 **PO（持久化對象）**，不包含業務邏輯
 * - 業務層應透過 Repository Adapter（如 OrderRepositoryImpl）轉換為 Entity
 *
 * @author yuan Created on 2025/08/05.
 */
public interface SpringDataImportOrderJpaRepository extends JpaRepository<ImportOrderPo, Long> {

    /**
     * 根據 UUID 查詢進口訂單
     */
    Optional<ImportOrderPo> findByUuid(UUID uuid);

    /**
     * 根據狀態查詢進口訂單
     */
    List<ImportOrderPo> findByStatus(OrderStatus status);

    /**
     * 根據櫃號查詢進口訂單
     */
    List<ImportOrderPo> findByContainerNumber(String containerNumber);

    /**
     * 根據船公司查詢進口訂單
     */
    List<ImportOrderPo> findByShippingCompany(String shippingCompany);
}
