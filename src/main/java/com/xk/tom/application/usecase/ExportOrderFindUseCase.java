package com.xk.tom.application.usecase;

import com.xk.tom.application.model.ExportOrderResponseDto;
import com.xk.tom.application.model.OrderResponseDto;
import com.xk.tom.domain.model.enums.OrderStatus;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

/**
 * 📌 ExportOrderFindUseCase
 * - 查詢出口訂單用例
 *
 * @author yuan Created on 2025/08/05.
 */
public interface ExportOrderFindUseCase {
    ExportOrderResponseDto getByUuid(UUID uuid);

    List<OrderResponseDto> getByStatus(OrderStatus status);

    List<OrderResponseDto> findAll();

//    ExportOrderResponseDto getExportOrder(@NotNull ExportOrderDTO request);
//
//    List<OrderResponseDTO> getOrderByOrderTypeExport();
//
//    List<OrderResponseDTO> getOrderByCustomerNameAndOrderTypeExport(String customerName);
//
//    List<OrderResponseDTO> getExportOrderByKeyWord(String keyWord);
}
