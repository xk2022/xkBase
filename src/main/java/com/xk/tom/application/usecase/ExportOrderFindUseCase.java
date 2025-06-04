package com.xk.tom.application.usecase;

import com.xk.tom.application.model.ExportOrderDTO;
import com.xk.tom.application.model.ExportOrderResponseDTO;
import com.xk.tom.application.model.OrderResponseDTO;
import jakarta.validation.constraints.NotNull;

import java.text.ParseException;
import java.util.List;

public interface ExportOrderFindUseCase {
    ExportOrderResponseDTO getExportOrder(@NotNull ExportOrderDTO request);

    List<OrderResponseDTO> getOrderByOrderTypeExport();

    List<OrderResponseDTO> getOrderByCustomerNameAndOrderTypeExport(String customerName);

    List<OrderResponseDTO> getExportOrderByKeyWord( String keyWord);
}
