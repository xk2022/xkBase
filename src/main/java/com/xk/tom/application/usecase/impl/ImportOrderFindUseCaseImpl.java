package com.xk.tom.application.usecase.impl;

import com.xk.tom.application.mapper.ImportOrderMapper;
import com.xk.tom.application.model.ImportOrderQueryDto;
import com.xk.tom.application.model.OrderResponseDto;
import com.xk.tom.application.usecase.ImportOrderFindUseCase;
import com.xk.tom.domain.service.ImportOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @author yuan Created on 2025/08/05.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ImportOrderFindUseCaseImpl implements ImportOrderFindUseCase {

    private final ImportOrderService service;
    private final ImportOrderMapper mapper;

    @Override
    public OrderResponseDto getByUuid(UUID uuid) {
        log.info("[UseCase] 查詢進口訂單 by uuid={}", uuid);
        var entity = service.findByUuid(uuid);
        return mapper.toResponseDto(entity);
    }

    @Override
    public Page<OrderResponseDto> getAll(Pageable pageable) {
        log.info("[UseCase] 分頁查詢進口訂單 page={}", pageable);
        return service.findAll(pageable)
                .map(mapper::toResponseDto);
    }

    @Override
    public List<OrderResponseDto> query(ImportOrderQueryDto query) {
        log.info("[UseCase] 複合條件查詢進口訂單 query={}", query);
        return service.findByCondition(query).stream()
                .map(mapper::toResponseDto)
                .toList();
    }

}
