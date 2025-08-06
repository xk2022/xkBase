package com.xk.tom.controller.api;

import com.xk.tom.application.model.ExportOrderRequestDto;
import com.xk.tom.application.model.ExportOrderResponseDto;
import com.xk.tom.application.model.OrderResponseDto;
import com.xk.tom.application.usecase.ExportOrderFindUseCase;
import com.xk.tom.application.usecase.ExportOrderManageUseCase;
import com.xk.tom.domain.model.enums.OrderStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * 📌 ExportOrderRestController
 * - 專門處理出口訂單 API
 *
 * @author yuan Created on 2025/08/05.
 */
@RestController
@RequestMapping("/api/export-orders")
@RequiredArgsConstructor
@Tag(name = "Export Order API", description = "出口訂單相關操作 API")
@Slf4j
public class ExportOrderRestController {

    private final ExportOrderFindUseCase findUseCase;
    private final ExportOrderManageUseCase manageUseCase;

    @GetMapping("/{uuid}")
    @Operation(summary = "查詢單筆出口訂單", description = "根據 UUID 查詢出口訂單")
    public ExportOrderResponseDto getByUuid(@PathVariable UUID uuid) {
        log.info("[API] 查詢出口訂單 uuid={}", uuid);
        return findUseCase.getByUuid(uuid);
    }

    @GetMapping
    @Operation(summary = "查詢出口訂單清單", description = "根據狀態查詢出口訂單列表")
    public List<OrderResponseDto> getByStatus(@RequestParam(required = false) OrderStatus status) {
        log.info("[API] 查詢出口訂單列表 status={}", status);
        return (status != null) ? findUseCase.getByStatus(status) : findUseCase.findAll();
    }

    @PostMapping
    @Operation(summary = "建立出口訂單", description = "建立一筆新的出口訂單")
    public ExportOrderResponseDto create(@Valid @RequestBody ExportOrderRequestDto request) {
        log.info("[API] 建立出口訂單 request={}", request);
        return manageUseCase.create(request);
    }

    @PutMapping("/{uuid}")
    @Operation(summary = "更新出口訂單", description = "更新指定 UUID 的出口訂單")
    public ExportOrderResponseDto update(@PathVariable UUID uuid,
                                         @Valid @RequestBody ExportOrderRequestDto request) {
        log.info("[API] 更新出口訂單 uuid={}, request={}", uuid, request);
        return manageUseCase.update(uuid, request);
    }

    @DeleteMapping("/{uuid}")
    @Operation(summary = "刪除出口訂單", description = "刪除指定 UUID 的出口訂單（僅限 pending 狀態）")
    public void delete(@PathVariable UUID uuid) {
        log.info("[API] 刪除出口訂單 uuid={}", uuid);
        manageUseCase.delete(uuid);
    }

}
