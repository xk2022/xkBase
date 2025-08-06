package com.xk.tom.application.usecase;

import com.xk.tom.application.model.*;
import jakarta.validation.Valid;

import java.util.UUID;

/**
 * 📌 ExportOrderManageUseCase
 * - 管理出口訂單用例
 *
 * @author yuan Created on 2025/08/05.
 */
public interface ExportOrderManageUseCase {

    OrderResponseDto save(@Valid ExportOrderRequestDto request);

    void delete(UUID uuid);

    OrderResponseDto assign(UUID uuid, AssignOrderRequestDto cmd);

    OrderResponseDto updateStatus(UUID uuid, UpdateOrderStatusRequestDto cmd);

    OrderResponseDto restore(UUID uuid);

}