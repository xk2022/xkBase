package com.xk.tom.application.usecase.impl;

import com.xk.tom.application.mapper.ExportOrderMapper;
import com.xk.tom.application.model.ExportOrderQueryDto;
import com.xk.tom.application.model.OrderResponseDto;
import com.xk.tom.application.usecase.ExportOrderFindUseCase;
import com.xk.tom.domain.service.ExportOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * 📌 ExportOrderFindUseCaseImpl
 * - 出口訂單查詢用例的實作
 * <p>
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
public class ExportOrderFindUseCaseImpl implements ExportOrderFindUseCase {

    private final ExportOrderService service;
    private final ExportOrderMapper mapper;

    @Override
    public OrderResponseDto getByUuid(UUID uuid) {
        log.info("[UseCase] 查詢出口訂單 by uuid={}", uuid);
        var entity = service.findByUuid(uuid);
        return mapper.toResponseDto(entity);
    }

    @Override
    public Page<OrderResponseDto> findAll(Pageable pageable) {
        log.info("[UseCase] 分頁查詢出口訂單 page={}", pageable);
        return service.findAll(pageable)
                .map(mapper::toResponseDto);
    }

    @Override
    public List<OrderResponseDto> query(ExportOrderQueryDto query) {
        log.info("[UseCase] 複合條件查詢出口訂單 query={}", query);
        return service.findByCondition(query).stream()
                .map(mapper::toResponseDto)
                .toList();
    }

}
