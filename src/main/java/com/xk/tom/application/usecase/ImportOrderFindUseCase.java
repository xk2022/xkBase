package com.xk.tom.application.usecase;

import com.xk.tom.application.model.ImportOrderResponseDto;
import com.xk.tom.domain.model.enums.OrderStatus;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

/**
 * 📌 ImportOrderFindUseCase
 * - 查詢進口訂單用例
 *
 * @author yuan Created on 2025/08/05.
 */
public interface ImportOrderFindUseCase {

    /**
     * 流程：
     * Controller 呼叫 UseCase (帶入 uuid)
     * UseCase 呼叫 Repository → importOrderRepository.findByUuid(uuid)
     * 如果找不到 → 拋出例外
     * Mapper → entity → responseDto
     * 回傳 ResponseDto
     */
    ImportOrderResponseDto getByUuid(UUID uuid);

    List<ImportOrderResponseDto> getByStatus(OrderStatus status);

//    ImportOrderResponseDto getImportOrder(@NotNull ImportOrderDTO request);
//
//    List<OrderResponseDTO> getOrderByOrderTypeImport();
//
//    List<OrderResponseDTO> getOrderByCustomerNameAndOrderTypeImport(String customerName);
//
//    List<OrderResponseDTO> getImportOrderByKeyWord( String keyWord);
}
