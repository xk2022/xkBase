package com.xk.tom.application.usecase;

import com.xk.tom.application.model.ImportOrderDTO;
import com.xk.tom.application.model.ImportOrderResponseDTO;
import com.xk.tom.application.model.OrderResponseDTO;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public interface ImportOrderFindUseCase {
    ImportOrderResponseDTO getImportOrder(@NotNull ImportOrderDTO request);

    List<OrderResponseDTO> getOrderByOrderTypeImport();

    List<OrderResponseDTO> getOrderByCustomerNameAndOrderTypeImport(String customerName);
}
