package com.xk.tom.application.usecase.impl;

import com.xk.tom.application.model.ImportOrderResponseDto;
import com.xk.tom.application.usecase.ImportOrderFindUseCase;
import com.xk.tom.domain.model.enums.OrderStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImportOrderFindUseCaseImpl implements ImportOrderFindUseCase {

    @Override
    public ImportOrderResponseDto getByUuid(UUID uuid) {
        return null;
    }

    @Override
    public List<ImportOrderResponseDto> getByStatus(OrderStatus status) {
        return List.of();
    }

//    private final ImportOrderService importOrderService;
//
//    @Override
//    public ImportOrderResponseDto getImportOrder(ImportOrderDTO request) {
//        ImportOrderResponseDto responseDTO = new ImportOrderResponseDto();
//
//        Optional<ImportOrderAggreate> aggreate = importOrderService.getImportOrder(request.orderid());
//        if (aggreate.isPresent()) {
//            ImportOrderAggreate importOrderAggreate = aggreate.get();
//            responseDTO = XkBeanUtils.copyProperties(importOrderAggreate, ImportOrderResponseDto::new);
////
//            return responseDTO;
//        } else {
//            return responseDTO;
//        }
//    }
//
//    @Override
//    public List<OrderResponseDTO> getOrderByOrderTypeImport() {
//        List<ImportOrderAggreate> importOrderAggreates = importOrderService.getOrderByOrderTypeImport();
//        List<OrderResponseDTO> orderResponseDTOS = new ArrayList<>();
//        orderResponseDTOS = XkBeanUtils.copyListProperties(importOrderAggreates, OrderResponseDTO::new);
//        return orderResponseDTOS;
//    }
//
//    @Override
//    public List<OrderResponseDTO> getOrderByCustomerNameAndOrderTypeImport(String customerName) {
//        List<ImportOrderAggreate> importOrderAggreates = importOrderService.getOrderByCustomerNameAndOrderTypeImport(customerName);
//        List<OrderResponseDTO> orderResponseDTOS = new ArrayList<>();
//        orderResponseDTOS = XkBeanUtils.copyListProperties(importOrderAggreates, OrderResponseDTO::new);
//
//        return orderResponseDTOS;
//    }
//
//    @Override
//    public List<OrderResponseDTO> getImportOrderByKeyWord(String keyWord) {
//        List<ImportOrderAggreate> importOrderAggreates = importOrderService.getImportOrderByKeyWord(keyWord);
//        List<OrderResponseDTO> orderResponseDTOS = new ArrayList<>();
//        orderResponseDTOS = XkBeanUtils.copyListProperties(importOrderAggreates, OrderResponseDTO::new);
//        return orderResponseDTOS;
//    }
}
