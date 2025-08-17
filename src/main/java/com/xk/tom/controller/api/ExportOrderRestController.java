package com.xk.tom.controller.api;

import com.xk.common.base.BaseResult;
import com.xk.tom.application.model.ExportOrderQueryDto;
import com.xk.tom.application.model.ExportOrderRequestDto;
import com.xk.tom.application.model.ExportOrderResponseDto;
import com.xk.tom.application.model.OrderResponseDto;
import com.xk.tom.application.usecase.ExportOrderFindUseCase;
import com.xk.tom.application.usecase.ExportOrderManageUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * 📌 ExportOrderRestController（出口專用）
 * - 用途：處理 出口訂單（export） 專屬操作
 * - 統一使用 BaseResult 包裝回應
 * - 搭配 Swagger 提供 API 說明
 *
 * @author yuan Created on 2025/08/05.
 */
@RestController
@RequestMapping("/api/export-orders")
@RequiredArgsConstructor
@Tag(name = "Export Order API", description = "出口訂單相關操作 API")
@Slf4j
@Validated
public class ExportOrderRestController {

    private final ExportOrderFindUseCase findUseCase;
    private final ExportOrderManageUseCase manageUseCase;

    @GetMapping("/{uuid}")
    @Operation(summary = "查詢單筆出口訂單", description = "根據 UUID 查詢出口訂單")
    public BaseResult<OrderResponseDto> getByUuid(@PathVariable UUID uuid) {
        log.info("[API] 查詢出口訂單 uuid={}", uuid);
        OrderResponseDto result = findUseCase.getByUuid(uuid);
        if (result != null) {
            return BaseResult.success(result, "成功取得訂單資料");
        }
        return BaseResult.failure(HttpStatus.NOT_FOUND, "未找到對應的訂單", null);
    }

    @PostMapping
    @Operation(summary = "查詢出口訂單清單", description = "根據查詢條件查詢出口訂單")
    public BaseResult<List<OrderResponseDto>> query(@Valid @RequestBody ExportOrderQueryDto query) {
        log.info("[API] 查詢出口訂單清單 query={}", query);
        List<OrderResponseDto> list = findUseCase.query(query);
        return BaseResult.success(list, "成功取得出口訂單列表");
    }

    @PostMapping("/save")
    @Operation(summary = "建立或更新出口訂單", description = "如果 UUID 存在 → 更新，否則 → 建立")
    public BaseResult<OrderResponseDto> save(@Valid @RequestBody ExportOrderRequestDto request) {
        log.info("[API] Save 出口訂單 request={}", request);
        OrderResponseDto result = manageUseCase.save(request);
        return BaseResult.success(result, "出口訂單處理成功");
    }

    @DeleteMapping("/{uuid}")
    @Operation(summary = "刪除出口訂單", description = "刪除指定 UUID 的出口訂單（僅限 pending 狀態）")
    public BaseResult<Void> delete(@PathVariable UUID uuid) {
        log.info("[API] 刪除出口訂單 uuid={}", uuid);
        manageUseCase.delete(uuid);
        return BaseResult.success(null, "出口訂單刪除成功");
    }

    /**
     * TODO
     * 指派作業 /api/export-orders/{uuid}/assign
     * 更新狀態 /api/export-orders/{uuid}/status
     * 批次匯入 /api/export-orders/batch
     * 匯出報表 /api/export-orders/reports/daily
     */
}
