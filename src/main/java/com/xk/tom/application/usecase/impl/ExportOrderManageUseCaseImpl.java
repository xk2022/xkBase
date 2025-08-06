package com.xk.tom.application.usecase.impl;

import com.xk.tom.application.mapper.ExportOrderMapper;
import com.xk.tom.application.model.*;
import com.xk.tom.application.usecase.ExportOrderManageUseCase;
import com.xk.tom.domain.model.bo.ExportOrderBo;
import com.xk.tom.domain.service.ExportOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * 📌 ExportOrderManageUseCaseImpl
 * - 出口訂單管理用例的實作
 *
 * Application Layer:
 * - 負責協調流程，不處理業務規則
 * - 呼叫 Domain Service 執行商業邏輯
 * - Mapper 負責轉換 Entity ↔ DTO
 *
 * @author yuan Created on 2025/08/06.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ExportOrderManageUseCaseImpl implements ExportOrderManageUseCase {

    private final ExportOrderService service;
    private final ExportOrderMapper mapper;

    @Override
    public OrderResponseDto save(ExportOrderRequestDto request) {
        ExportOrderBo result;
        if (request.getUuid() == null) {
            log.info("[UseCase] 建立出口訂單 request={}", request);
            var cmd = mapper.toCreateCmd(request);
            result = service.create(cmd);
        } else {
            log.info("[UseCase] 更新出口訂單 uuid={}, request={}", request.getUuid(), request);
            var cmd = mapper.toUpdateCmd(request);
            result = service.update(request.getUuid(), cmd);
        }
        return mapper.toResponseDto(result);
    }

    @Override
    public void delete(UUID uuid) {
        log.info("[UseCase] 刪除出口訂單 uuid={}", uuid);
        service.delete(uuid);
    }

    @Override
    public OrderResponseDto assign(UUID uuid, AssignOrderRequestDto request) {
        log.info("[UseCase] 指派出口訂單 uuid={}, request={}", uuid, request);
        var cmd = mapper.toAssignCmd(request);
        var updated = service.assign(uuid, cmd);
        return mapper.toResponseDto(updated);
    }

    @Override
    public OrderResponseDto updateStatus(UUID uuid, UpdateOrderStatusRequestDto request) {
        log.info("[UseCase] 更新出口訂單狀態 uuid={}, request={}", uuid, request);
        var cmd = mapper.toUpdateStatusCmd(request);
        var updated = service.updateStatus(uuid, cmd);
        return mapper.toResponseDto(updated);
    }

    @Override
    public OrderResponseDto restore(UUID uuid) {
        log.info("[UseCase] 恢復出口訂單 uuid={}", uuid);
        var updated = service.restore(uuid);
        return mapper.toResponseDto(updated);
    }

}
