package com.xk.tom.application.usecase;

import com.xk.tom.application.model.*;
import jakarta.validation.Valid;

import java.util.UUID;

/**
 * 📌 ImportOrderManageUseCase
 * - 管理進口訂單用例 (建立、修改、刪除)
 *
 * @author yuan Created on 2025/08/05.
 */
public interface ImportOrderManageUseCase {

    OrderResponseDto save(@Valid ImportOrderRequestDto request);

    /**
     * 流程：
     * Controller 呼叫 UseCase (uuid)
     * Repository.findByUuid(uuid)
     * 不是已派送/已完成 → 可以刪除
     * Repository.save(entity with deleted flag = true)
     * 不需要回傳 DTO，直接 200 OK
     */
    void delete(UUID uuid);

    /**
     * 指派司機與車輛（自動或手動）
     * 修改狀態 → ASSIGNED
     * 記錄：assigned_vehicle_id、assigned_driver_id、assigned_by
     */
    OrderResponseDto assign(UUID uuid, AssignOrderRequestDto cmd);

    /**
     * 允許司機或管理員回報進度
     * 例如：PENDING → IN_TRANSIT → COMPLETED
     * 記錄：status、操作者 userId、timestamp
     */
    OrderResponseDto updateStatus(UUID uuid, UpdateOrderStatusRequestDto cmd);

//    List<OrderResponseDto> batchCreate(List<CreateImportOrderCmd> cmds);

    OrderResponseDto restore(UUID uuid);

}
