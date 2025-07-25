package com.xk.tom.application.usecase;

import com.xk.tom.application.model.ExportOrderRequestDTO;
import com.xk.tom.application.model.ExportOrderResponseDTO;
import com.xk.tom.application.model.ImportOrderRequestDTO;

import java.text.ParseException;

public interface ExportOrderUpdateUseCase {

    ExportOrderResponseDTO updateExportOrder(Long exportId, ExportOrderRequestDTO request) throws ParseException;
}
