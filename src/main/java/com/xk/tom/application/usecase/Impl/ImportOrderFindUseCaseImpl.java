package com.xk.tom.application.usecase.Impl;

import com.xk.common.util.XkBeanUtils;
import com.xk.tom.application.model.ImportOrderDTO;
import com.xk.tom.application.model.ImportOrderResponseDTO;
import com.xk.tom.application.model.OrderResponseDTO;
import com.xk.tom.application.usecase.ImportOrderFindUseCase;
import com.xk.tom.domain.model.aggreate.ImportOrderAggreate;
import com.xk.tom.domain.service.ImportOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImportOrderFindUseCaseImpl implements ImportOrderFindUseCase {

    private final ImportOrderService importOrderService;
    @Override
    public ImportOrderResponseDTO getImportOrder(ImportOrderDTO request) {
        ImportOrderResponseDTO responseDTO = new ImportOrderResponseDTO();

        Optional<ImportOrderAggreate> aggreate = importOrderService.getImportOrder(request.orderid());
        if (aggreate.isPresent()) {
            ImportOrderAggreate importOrderAggreate = aggreate.get();
            responseDTO = XkBeanUtils.copyProperties(importOrderAggreate , ImportOrderResponseDTO::new);
//
            return responseDTO;
        }else {
            return responseDTO;
        }
    }

    @Override
    public List<OrderResponseDTO> getOrderByOrderTypeImport() {
        List<ImportOrderAggreate> importOrderAggreates = importOrderService.getOrderByOrderTypeImport();
        List<OrderResponseDTO> orderResponseDTOS = new ArrayList<>();
        orderResponseDTOS = XkBeanUtils.copyListProperties(importOrderAggreates , OrderResponseDTO::new);
        return orderResponseDTOS;
    }

    @Override
    public List<OrderResponseDTO> getOrderByCustomerNameAndOrderTypeImport(String customerName) {
        List<ImportOrderAggreate> importOrderAggreates = importOrderService.getOrderByCustomerNameAndOrderTypeImport(customerName);
        List<OrderResponseDTO> orderResponseDTOS = new ArrayList<>();
        orderResponseDTOS =XkBeanUtils.copyListProperties(importOrderAggreates , OrderResponseDTO::new);

        return orderResponseDTOS;
    }

    @Override
    public List<OrderResponseDTO> getImportOrderByKeyWord(String keyWord) {
        List<ImportOrderAggreate> importOrderAggreates = importOrderService.getImportOrderByKeyWord(keyWord);
        List<OrderResponseDTO> orderResponseDTOS = new ArrayList<>();
        orderResponseDTOS =XkBeanUtils.copyListProperties(importOrderAggreates ,OrderResponseDTO::new );
        return orderResponseDTOS;
    }
}
