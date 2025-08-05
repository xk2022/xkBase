package com.xk.tom.controller.api;

import com.xk.tom.application.model.ImportOrderRequestDto;
import com.xk.tom.application.model.ImportOrderResponseDto;
import com.xk.tom.application.usecase.ImportOrderFindUseCase;
import com.xk.tom.application.usecase.ImportOrderManageUseCase;
import com.xk.tom.domain.model.enums.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * 📌 ImportOrderRestController
 * - 進口訂單 API
 */
@RestController
@RequestMapping("/api/import-orders")
@RequiredArgsConstructor
public class ImportOrderRestController {

    private final ImportOrderFindUseCase findUseCase;
    private final ImportOrderManageUseCase manageUseCase;

    @GetMapping("/{uuid}")
    public ImportOrderResponseDto getByUuid(@PathVariable UUID uuid) {
        return findUseCase.getByUuid(uuid);
    }

    @GetMapping
    public List<ImportOrderResponseDto> getByStatus(@RequestParam OrderStatus status) {
        return findUseCase.getByStatus(status);
    }

    @PostMapping
    public ImportOrderResponseDto create(@RequestBody ImportOrderRequestDto request) {
        return manageUseCase.create(request);
    }

    @PutMapping("/{uuid}")
    public ImportOrderResponseDto update(@PathVariable UUID uuid,
                                         @RequestBody ImportOrderRequestDto request) {
        return manageUseCase.update(uuid, request);
    }

    @DeleteMapping("/{uuid}")
    public void delete(@PathVariable UUID uuid) {
        manageUseCase.delete(uuid);
    }
}