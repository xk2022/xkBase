package com.xk.tom.application.usecase;

import com.xk.tom.application.model.ImportOrderRequestDTO;
import com.xk.tom.application.model.ImportOrderResponseDTO;

import java.text.ParseException;

public interface ImportOrderUpdateUseCase {


    ImportOrderResponseDTO updateImportOrder(Long importId, ImportOrderRequestDTO request) throws ParseException;
}
