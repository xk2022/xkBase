package com.xk.tom.application.usecase;

import com.xk.tom.application.model.ExportOrderRequestDto;
import com.xk.tom.application.model.ExportOrderResponseDto;

import java.util.UUID;

/**
 * 📌 ExportOrderManageUseCase
 * - 管理出口訂單用例
 *
 * @author yuan Created on 2025/08/05.
 */
public interface ExportOrderManageUseCase {
    ExportOrderResponseDto create(ExportOrderRequestDto request);

    ExportOrderResponseDto update(UUID uuid, ExportOrderRequestDto request);

    void delete(UUID uuid);
}