package com.xk.tom.application.usecase.impl;

import com.xk.tom.application.mapper.ImportOrderMapper;
import com.xk.tom.application.model.*;
import com.xk.tom.application.usecase.ImportOrderManageUseCase;
import com.xk.tom.domain.model.bo.ImportOrderBo;
import com.xk.tom.domain.service.ImportOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * 📌 ImportOrderManageUseCaseImpl
 * - 進口訂單管理用例的實作
 * <p>
 * Application Layer:
 * - 負責協調流程，不處理業務規則
 * - 呼叫 Domain Service 執行商業邏輯
 * - Mapper 負責轉換 Entity ↔ DTO
 *
 * @author yuan Created on 2025/08/05.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ImportOrderManageUseCaseImpl implements ImportOrderManageUseCase {

    private final ImportOrderService service;
    private final ImportOrderMapper mapper;

    /**
     * OrderResponseDto create(ImportOrderRequestDto request);
     * 流程：
     * Controller 呼叫 UseCase (帶入 request)
     * Mapper → requestDto → entity
     * 初始化 Entity 狀態（例如 status = PENDING）
     * Repository.save(entity)
     * Mapper → entity → responseDto
     * 回傳 ResponseDto
     */
    /**
     * OrderResponseDto update(UUID uuid, ImportOrderRequestDto request);
     * 流程：
     * Controller 呼叫 UseCase (uuid + request)
     * Repository.findByUuid(uuid) → 取出 Entity
     * 更新 Entity 欄位（可能要用 Cmd 封裝）
     * 呼叫 Entity 的 狀態檢查邏輯（避免違反業務規則）
     * Repository.save(entity)
     * Mapper → entity → responseDto
     * 回傳 ResponseDto
     */
    @Override
    public OrderResponseDto save(ImportOrderRequestDto request) {
        ImportOrderBo result;
        // ✅ 呼叫 Service，交給領域處理
        if (request.getUuid() == null) {
            log.info("[UseCase] 建立進口訂單 request={}", request);
            ImportOrderCreateCmd cmd = mapper.toCreateCmd(request);
            result = service.create(cmd);
        } else {
            log.info("[UseCase] 更新進口訂單 uuid={}, request={}", request.getUuid(), request);
            ImportOrderUpdateCmd cmd = mapper.toUpdateCmd(request);
            result = service.update(request.getUuid(), cmd);
        }
        return mapper.toResponseDto(result);
    }

    @Override
    public void delete(UUID uuid) {
        log.info("[UseCase] 刪除進口訂單 uuid={}", uuid);
        service.delete(uuid); // ✅ 直接呼叫 Service，交給領域處理
    }

    @Override
    public OrderResponseDto assign(UUID uuid, AssignOrderRequestDto request) {
        log.info("[UseCase] 指派進口訂單 uuid={}, request={}", uuid, request);
        var cmd = mapper.toAssignCmd(request);
        var updated = service.assign(uuid, cmd);
        return mapper.toResponseDto(updated);
    }

    @Override
    public OrderResponseDto updateStatus(UUID uuid, UpdateOrderStatusRequestDto request) {
        log.info("[UseCase] 更新進口訂單狀態 uuid={}, request={}", uuid, request);
        var cmd = mapper.toUpdateStatusCmd(request);
        var updated = service.updateStatus(uuid, cmd);
        return mapper.toResponseDto(updated);
    }

    @Override
    public OrderResponseDto restore(UUID uuid) {
        log.info("[UseCase] 恢復進口訂單 uuid={}", uuid);
        var updated = service.restore(uuid);
        return mapper.toResponseDto(updated);
    }

}
