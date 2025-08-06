package com.xk.tom.domain.dao.repository;

import com.xk.tom.application.model.ExportOrderQueryDto;
import com.xk.tom.domain.model.entity.ExportOrderEntity;
import jakarta.validation.constraints.NotNull;

import java.util.List;

/**
 * 📌 ExportOrderRepository
 * <p>
 * - 負責出口訂單（ExportOrderEntity）的資料存取
 * - 僅定義業務需要的方法
 * - 對外只暴露 **Entity**，隱藏 JPA 與 PO 細節
 * <p>
 * ⚠️ 注意：
 * - 不直接繼承 JpaRepository（避免 Domain 層依賴 Infra）
 * - 繼承泛型 OrderRepository
 *
 * @author yuan Created on 2025/08/04.
 */
public interface ExportOrderRepository extends OrderRepository<ExportOrderEntity> {

    /**
     * 根據複合條件查詢出口訂單
     *
     * @param condition 查詢條件
     * @return 符合條件的出口訂單清單
     */
    List<ExportOrderEntity> findByCondition(@NotNull ExportOrderQueryDto condition);

}
