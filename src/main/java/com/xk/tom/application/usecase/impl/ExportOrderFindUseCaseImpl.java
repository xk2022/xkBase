package com.xk.tom.application.usecase.impl;

import com.xk.tom.application.model.ExportOrderResponseDto;
import com.xk.tom.application.usecase.ExportOrderFindUseCase;
import com.xk.tom.domain.model.enums.OrderStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExportOrderFindUseCaseImpl implements ExportOrderFindUseCase {
    @Override
    public ExportOrderResponseDto getByUuid(UUID uuid) {
        return null;
    }

    @Override
    public List<ExportOrderResponseDto> getByStatus(OrderStatus status) {
        return List.of();
    }

//    private final ExportOrderService exportOrderService;
//
//    @Override
//    public ExportOrderResponseDto getExportOrder(ExportOrderDTO request) {
//        ExportOrderResponseDto exportOrderResponseDTO = new ExportOrderResponseDto();
//        exportOrderResponseDTO = exportOrderService.getExportOrder(request.orderid());
//        return exportOrderResponseDTO;
//    }
//
//    @Override
//    public List<OrderResponseDTO> getOrderByOrderTypeExport() {
//        List<ExportOrderAggreate> exportOrderAggreates = exportOrderService.getOrderByOrderTypeImport();
//        List<OrderResponseDTO> orderResponseDTOS = new ArrayList<>();
//        orderResponseDTOS = XkBeanUtils.copyListProperties(exportOrderAggreates, OrderResponseDTO::new);
//        return orderResponseDTOS;
//    }
//
//    @Override
//    public List<OrderResponseDTO> getOrderByCustomerNameAndOrderTypeExport(String customerName) {
//        List<ExportOrderAggreate> exportOrderAggreates = exportOrderService.getOrderByCustomerNameAndOrderTypeExport(customerName);
//        List<OrderResponseDTO> orderResponseDTOS = new ArrayList<>();
//        orderResponseDTOS = XkBeanUtils.copyListProperties(exportOrderAggreates, OrderResponseDTO::new);
//        return orderResponseDTOS;
//    }
//
//    @Override
//    public List<OrderResponseDTO> getExportOrderByKeyWord(String keyWord) {
//        List<ExportOrderAggreate> exportOrderAggreates = exportOrderService.getImportOrderByKeyWord(keyWord);
//        List<OrderResponseDTO> orderResponseDTOS = new ArrayList<>();
//        orderResponseDTOS = XkBeanUtils.copyListProperties(exportOrderAggreates, OrderResponseDTO::new);
//        return orderResponseDTOS;
//    }
}
