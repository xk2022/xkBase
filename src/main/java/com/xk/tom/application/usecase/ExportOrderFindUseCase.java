package com.xk.tom.application.usecase;

import com.xk.tom.application.model.ExportOrderDTO;
import com.xk.tom.application.model.ExportOrderResponseDTO;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public interface ExportOrderFindUseCase {
    List<ExportOrderResponseDTO> getExportOrder(@NotNull ExportOrderDTO request);
}
