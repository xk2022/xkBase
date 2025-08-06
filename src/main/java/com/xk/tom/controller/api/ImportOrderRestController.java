package com.xk.tom.controller.api;

import com.xk.tom.application.model.ImportOrderQueryDto;
import com.xk.tom.application.model.ImportOrderRequestDto;
import com.xk.tom.application.model.OrderResponseDto;
import com.xk.tom.application.usecase.ImportOrderFindUseCase;
import com.xk.tom.application.usecase.ImportOrderManageUseCase;
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
 * 📌 ImportOrderRestController（進口專用）
 * 用途：處理 進口訂單（import） 專屬的操作
 * <p>
 * 👉 這一隻是「進口訂單專用」，確保進口欄位（如 do_number, return_yard）有正確處理。
 *
 * @author yuan Created on 2025/08/05.
 */
@RestController
@RequestMapping("/api/import-orders")
@RequiredArgsConstructor
@Tag(name = "Import Order API", description = "進口訂單相關操作 API")
@Slf4j
public class ImportOrderRestController {

    private final ImportOrderFindUseCase findUseCase;
    private final ImportOrderManageUseCase manageUseCase;

    @GetMapping("/{uuid}")
    @Operation(summary = "查詢單筆進口訂單", description = "根據 UUID 查詢進口訂單")
    public OrderResponseDto getByUuid(@PathVariable UUID uuid) {
        log.info("[API] 查詢進口訂單 uuid={}", uuid);
        return findUseCase.getByUuid(uuid);
    }

    @GetMapping
    @Operation(summary = "查詢進口訂單清單", description = "根據狀態查詢進口訂單列表")
    public List<OrderResponseDto> getByStatus(@RequestParam(required = false) OrderStatus status) {
        log.info("[API] 查詢進口訂單列表 status={}", status);
        ImportOrderQueryDto query = new ImportOrderQueryDto();
        query.setStstus(status);
        return (status != null) ? findUseCase.findByCondition(query) : findUseCase.getAll();
    }

    @PostMapping("/save")
    @Operation(summary = "建立或更新進口訂單", description = "如果 UUID 存在 → 更新，否則 → 建立")
    public OrderResponseDto save(@Valid @RequestBody ImportOrderRequestDto request) {
        log.info("[API] Save 進口訂單 request={}", request);
        return manageUseCase.save(request);
    }

    @DeleteMapping("/{uuid}")
    @Operation(summary = "刪除進口訂單", description = "刪除指定 UUID 的進口訂單（僅限 pending 狀態）")
    public void delete(@PathVariable UUID uuid) {
        log.info("[API] 刪除進口訂單 uuid={}", uuid);
        manageUseCase.delete(uuid);
    }

    /**
     * TODO
     * 指派作業 /api/orders/{uuid}/assign
     * 更新狀態 /api/orders/{uuid}/status
     * 批次匯入 /api/orders/batch
     * 匯出報表 /api/orders/reports/daily
     */

}
