package com.xk.tom.controller.api;

import com.xk.tom.application.model.ExportOrderRequestDto;
import com.xk.tom.application.model.ExportOrderResponseDto;
import com.xk.tom.application.usecase.ExportOrderFindUseCase;
import com.xk.tom.application.usecase.ExportOrderManageUseCase;
import com.xk.tom.domain.model.enums.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * 📌 ExportOrderRestController
 * - 出口訂單 API
 */
@RestController
@RequestMapping("/api/export-orders")
@RequiredArgsConstructor
public class ExportOrderRestController {

    private final ExportOrderFindUseCase findUseCase;
    private final ExportOrderManageUseCase manageUseCase;

    @GetMapping("/{uuid}")
    public ExportOrderResponseDto getByUuid(@PathVariable UUID uuid) {
        return findUseCase.getByUuid(uuid);
    }

    @GetMapping
    public List<ExportOrderResponseDto> getByStatus(@RequestParam OrderStatus status) {
        return findUseCase.getByStatus(status);
    }

    @PostMapping
    public ExportOrderResponseDto create(@RequestBody ExportOrderRequestDto request) {
        return manageUseCase.create(request);
    }

    @PutMapping("/{uuid}")
    public ExportOrderResponseDto update(@PathVariable UUID uuid,
                                         @RequestBody ExportOrderRequestDto request) {
        return manageUseCase.update(uuid, request);
    }

    @DeleteMapping("/{uuid}")
    public void delete(@PathVariable UUID uuid) {
        manageUseCase.delete(uuid);
    }
}
