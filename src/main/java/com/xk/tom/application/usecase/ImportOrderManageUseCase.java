package com.xk.tom.application.usecase;

import com.xk.tom.application.model.ImportOrderRequestDto;
import com.xk.tom.application.model.ImportOrderResponseDto;

import java.util.UUID;

/**
 * 📌 ImportOrderManageUseCase
 * - 管理進口訂單用例 (建立、修改、刪除)
 *
 * @author yuan Created on 2025/08/05.
 */
public interface ImportOrderManageUseCase {

    /**
     * 流程：
     * Controller 呼叫 UseCase (帶入 request)
     * Mapper → requestDto → entity
     * 初始化 Entity 狀態（例如 status = PENDING）
     * Repository.save(entity)
     * Mapper → entity → responseDto
     * 回傳 ResponseDto
     */
    ImportOrderResponseDto create(ImportOrderRequestDto request);

    /**
     * 流程：
     * Controller 呼叫 UseCase (uuid + request)
     * Repository.findByUuid(uuid) → 取出 Entity
     * 更新 Entity 欄位（可能要用 Cmd 封裝）
     * 呼叫 Entity 的 狀態檢查邏輯（避免違反業務規則）
     * Repository.save(entity)
     * Mapper → entity → responseDto
     * 回傳 ResponseDto
     */
    ImportOrderResponseDto update(UUID uuid, ImportOrderRequestDto request);

    /**
     * 流程：
     * Controller 呼叫 UseCase (uuid)
     * Repository.findByUuid(uuid)
     * 不是已派送/已完成 → 可以刪除
     * Repository.save(entity with deleted flag = true)
     * 不需要回傳 DTO，直接 200 OK
     */
    void delete(UUID uuid);

}
