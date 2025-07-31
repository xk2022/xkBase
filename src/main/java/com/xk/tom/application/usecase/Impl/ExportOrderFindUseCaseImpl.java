package com.xk.tom.application.usecase.Impl;

import com.xk.common.util.XkBeanUtils;
import com.xk.tom.application.model.ExportOrderDTO;
import com.xk.tom.application.model.ExportOrderResponseDTO;
import com.xk.tom.application.model.OrderResponseDTO;
import com.xk.tom.application.usecase.ExportOrderFindUseCase;
import com.xk.tom.domain.model.aggreate.ExportOrderAggreate;
import com.xk.tom.domain.model.aggreate.ImportOrderAggreate;
import com.xk.tom.domain.service.ExportOrderService;
import com.xk.tom.domain.service.ImportOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
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

    @Override
    public List<OrderResponseDTO> getOrderByOrderTypeExport() {
        List<ExportOrderAggreate> exportOrderAggreates = exportOrderService.getOrderByOrderTypeImport();
        List<OrderResponseDTO> orderResponseDTOS = new ArrayList<>();
        orderResponseDTOS = XkBeanUtils.copyListProperties(exportOrderAggreates , OrderResponseDTO::new);
        return orderResponseDTOS;
    }

    @Override
    public List<OrderResponseDTO> getOrderByCustomerNameAndOrderTypeExport(String customerName) {
        List<ExportOrderAggreate> exportOrderAggreates = exportOrderService.getOrderByCustomerNameAndOrderTypeExport(customerName);
        List<OrderResponseDTO> orderResponseDTOS = new ArrayList<>();
        orderResponseDTOS = XkBeanUtils.copyListProperties(exportOrderAggreates , OrderResponseDTO::new);
        return orderResponseDTOS;
    }

    @Override
    public List<OrderResponseDTO> getExportOrderByKeyWord(String keyWord) {
        List<ExportOrderAggreate> exportOrderAggreates = exportOrderService.getImportOrderByKeyWord(keyWord);
        List<OrderResponseDTO> orderResponseDTOS = new ArrayList<>();
        orderResponseDTOS =XkBeanUtils.copyListProperties(exportOrderAggreates ,OrderResponseDTO::new );
        return orderResponseDTOS;
    }
}
