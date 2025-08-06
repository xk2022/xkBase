package com.xk.tom.domain.dao.repository;

import com.xk.tom.application.model.ImportOrderQueryDto;
import com.xk.tom.domain.model.entity.ImportOrderEntity;
import com.xk.tom.domain.model.enums.OrderStatus;

import java.util.List;

/**
 * 📌 ImportOrderRepository
 * <p>
 * - 負責進口訂單（ImportOrderEntity）的資料存取
 * - 僅定義業務需要的方法
 * - 對外只暴露 **Entity**，隱藏 JPA 與 PO 細節
 * <p>
 * ⚠️ 注意：
 * - 不直接繼承 JpaRepository（避免 Domain 層依賴 Infra）
 * - 繼承泛型 OrderRepository
 *
 * @author yuan Created on 2025/08/04.
 */
public interface ImportOrderRepository extends OrderRepository<ImportOrderEntity> {

    List<ImportOrderEntity> findByCondition(ImportOrderQueryDto orderStatus);

}