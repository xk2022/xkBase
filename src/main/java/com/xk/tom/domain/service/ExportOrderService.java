package com.xk.tom.domain.service;

import com.xk.tom.application.model.*;
import com.xk.tom.domain.model.bo.ExportOrderBo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

/**
 * 📌 `ExportOrderService`
 * - 提供 **出口訂單（Order）** 的核心業務邏輯
 * - 與 ImportOrderService 結構對稱，保持一致性
 *
 * @author yuan Created on 2025/08/06.
 */
public interface ExportOrderService {

    /**
     * 根據 UUID 查詢出口訂單
     */
    ExportOrderBo findByUuid(UUID uuid);

    /**
     * 查詢全部出口訂單
     */
    List<ExportOrderBo> findAll();

    /**
     * 分頁查詢出口訂單
     */
    Page<ExportOrderBo> findAll(Pageable pageable);

    /**
     * 複合條件查詢出口訂單
     */
    List<ExportOrderBo> findByCondition(ExportOrderQueryDto condition);

    /**
     * 建立出口訂單
     */
    ExportOrderBo create(ExportOrderCreateCmd cmd);

    /**
     * 更新出口訂單
     */
    ExportOrderBo update(UUID uuid, ExportOrderUpdateCmd updateData);

    /**
     * 指派作業（司機、車輛）
     */
    ExportOrderBo assign(UUID uuid, OrderAssignCmd cmd);

    /**
     * 狀態更新（進度回報）
     */
    ExportOrderBo updateStatus(UUID uuid, OrderUpdateStatusCmd cmd);

    /**
     * 恢復出口訂單（軟刪除 → 還原）
     */
    ExportOrderBo restore(UUID uuid);

    /**
     * 刪除出口訂單（僅限 pending 狀態）
     */
    void delete(UUID uuid);

}
