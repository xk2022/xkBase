package com.xk.tom.domain.dao.repository;

import com.xk.tom.domain.model.entity.ExportOrderEntity;
import com.xk.tom.domain.model.enums.OrderStatus;
import com.xk.tom.infrastructure.persistence.model.po.ExportOrderPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * 📌 ExportOrderRepository
 *
 * - 負責出口訂單（ExportOrderEntity）的資料存取
 * - 僅定義業務需要的方法
 * - 對外只暴露 **Entity**，隱藏 JPA 與 PO 細節
 *
 * ⚠️ 注意：
 * - 不直接繼承 JpaRepository（避免 Domain 層依賴 Infra）
 * - 繼承泛型 OrderRepository
 *
 * @author yuan Created on 2025/08/04.
 */
public interface ExportOrderRepository extends OrderRepository<ExportOrderEntity> {
}