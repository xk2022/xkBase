package com.xk.tom.application.usecase.Impl;

import com.xk.tom.application.model.ExportOrderDTO;
import com.xk.tom.application.model.ExportOrderResponseDTO;
import com.xk.tom.application.usecase.ExportOrderFindUseCase;
import com.xk.tom.domain.service.ExportOrderService;
import com.xk.tom.domain.service.ImportOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExportOrderFindUseCaseImpl implements ExportOrderFindUseCase {

    private final ExportOrderService exportOrderService;
    @Override
    public ExportOrderResponseDTO getExportOrder(ExportOrderDTO request)  {
        ExportOrderResponseDTO exportOrderResponseDTO = new ExportOrderResponseDTO();
        exportOrderResponseDTO = exportOrderService.getExportOrder(request.orderid());
        return exportOrderResponseDTO;
    }
}
