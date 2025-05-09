package com.xk.tom.application.usecase.Impl;

import com.xk.tom.application.model.ExportOrderDTO;
import com.xk.tom.application.model.ExportOrderResponseDTO;
import com.xk.tom.application.usecase.ExportOrderFindUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExportOrderFindUseCaseImpl implements ExportOrderFindUseCase {
    @Override
    public List<ExportOrderResponseDTO> getExportOrder(ExportOrderDTO request) {
        return List.of();
    }
}
