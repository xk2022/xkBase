package com.xk.tom.domain.dao.repository;

import com.xk.tom.domain.model.entity.ExportOrderEntity;
import com.xk.tom.domain.model.entity.ImportOrderEntity;
import com.xk.tom.domain.model.enums.OrderStatus;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * 📌 `ImportOrderRepository`
 *
 * - 負責與 `orders` 資料表進行存取
 * - 使用 Spring Data JPA 提供標準 CRUD
 * - 支援 **自訂查詢方法**
 *
 * @author yuan Created on 2025/08/04.
 */
/**
 * 📌 ImportOrderRepository
 *
 * - 負責進口訂單（ImportOrderEntity）的資料存取
 * - 僅定義業務需要的方法
 * - 對外只暴露 **Entity**，隱藏 JPA 與 PO 細節
 *
 * ⚠️ 注意：
 * - 不直接繼承 JpaRepository（避免 Domain 層依賴 Infra）
 * - 繼承泛型 OrderRepository
 *
 * @author yuan Created on 2025/08/05.
 */
public interface ImportOrderRepository extends OrderRepository<ImportOrderEntity> {
}