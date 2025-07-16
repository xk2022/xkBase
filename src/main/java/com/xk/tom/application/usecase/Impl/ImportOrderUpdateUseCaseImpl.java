package com.xk.tom.application.usecase.Impl;

import com.xk.common.util.XkBeanUtils;
import com.xk.exapmleFolder.domain.model.demo.OrderStatusEnum;
import com.xk.tom.application.model.ImportOrderRequestDTO;
import com.xk.tom.application.model.ImportOrderResponseDTO;
import com.xk.tom.application.usecase.ImportOrderUpdateUseCase;
import com.xk.tom.domain.model.aggreate.ImportOrderAggreate;
import com.xk.tom.domain.model.bo.ImportOrderBO;
import com.xk.tom.domain.service.ImportOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImportOrderUpdateUseCaseImpl implements ImportOrderUpdateUseCase {


    private final ImportOrderService importOrderService;

    @Override
    public ImportOrderResponseDTO updateImportOrder(Long importId, ImportOrderRequestDTO request) throws ParseException {

        ImportOrderAggreate importOrder = importOrderService.findByImportId(importId)
                .orElseThrow(() -> new IllegalArgumentException("找不到對應的 ImportOrder，ID: " + importId));


        updateImportOrderFromRequest(importOrder, request);

        // 儲存 BO
        ImportOrderBO bo = XkBeanUtils.copyProperties(importOrder, ImportOrderBO::new);
        importOrderService.save(bo);

        return XkBeanUtils.copyProperties(importOrder, ImportOrderResponseDTO::new);
    }


    private void updateImportOrderFromRequest(ImportOrderAggreate order, ImportOrderRequestDTO req) {
        order.setImportDate(req.importDate());
        order.setDeliveryOrderLocation(req.deliveryOrderLocation());
        order.setShippingCompany(req.shippingCompany());
        order.setVesselVoyage(req.vesselVoyage());
        order.setContainerNumber(req.containerNumber());
        order.setContainerType(req.containerType());
        order.setContainerYard(req.containerYard());
        order.setLastPickupDate(req.lastPickupDate());
        order.setDeliveryLocation(req.deliveryLocation());
        order.setDeliveryDate(req.deliveryDate());
        order.setDeliveryTime(req.deliveryTime());
        order.setReturnYard(req.returnYard());
        order.setReturnDate(req.returnDate());
        order.setNote(req.note());

        // 使用安全轉換
        OrderStatusEnum status = OrderStatusEnum.valueOf(req.status());
        if (status == null) throw new IllegalArgumentException("無效的狀態：" + req.status());
        order.setStatus(status);

        order.setUpdatedBy(req.updatedBy());
        order.setUpdatedTime(req.updatedTime());
    }

}
