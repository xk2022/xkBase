package com.xk.tom.application.usecase;

import com.xk.tom.application.model.ImportOrderDTO;
import com.xk.tom.application.model.ImportOrderResponseDTO;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public interface ImportOrderFindUseCase {
    List<ImportOrderResponseDTO> getImportOrder(@NotNull ImportOrderDTO request);
}
