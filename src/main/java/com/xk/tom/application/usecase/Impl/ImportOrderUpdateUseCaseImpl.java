package com.xk.tom.application.usecase.Impl;

import com.xk.common.util.XkBeanUtils;
import com.xk.exapmleFolder.domain.model.demo.OrderStatusEnum;
import com.xk.tom.application.model.ImportOrderRequestDTO;
import com.xk.tom.application.model.ImportOrderResponseDTO;
import com.xk.tom.application.usecase.ImportOrderUpdateUseCase;
import com.xk.tom.domain.model.aggreate.ImportOrderAggreate;
import com.xk.tom.domain.service.ImportOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.time.ZonedDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImportOrderUpdateUseCaseImpl implements ImportOrderUpdateUseCase {


    private final ImportOrderService importOrderService;

    @Override
    @Transactional
    public ImportOrderResponseDTO updateImportOrder(Long importId, ImportOrderRequestDTO request) throws ParseException {

        ImportOrderAggreate importOrder = importOrderService.findByImportId(importId)
                .orElseThrow(() -> new IllegalArgumentException("找不到對應的 ImportOrder，ID: " + importId));


        updateImportOrderFromRequest(importOrder, request);

        // 儲存
        ImportOrderAggreate aggreate = importOrderService.update(importOrder);

        return XkBeanUtils.copyProperties(aggreate, ImportOrderResponseDTO::new);
    }


    @Transactional
    public void updateImportOrderFromRequest(ImportOrderAggreate order, ImportOrderRequestDTO req) {

        if (req.importDate() != null) {
            order.setImportDate(req.importDate());
        }

        if (req.deliveryOrderLocation() != null) {
            order.setDeliveryOrderLocation(req.deliveryOrderLocation());
        }

        if (req.shippingCompany() != null) {
            order.setShippingCompany(req.shippingCompany());
        }

        if (req.vesselVoyage() != null) {
            order.setVesselVoyage(req.vesselVoyage());
        }

        if (req.containerNumber() != null) {
            order.setContainerNumber(req.containerNumber());
        }

        if (req.containerYard() != null) {
            order.setContainerYard(req.containerYard());
        }

        if (req.lastPickupDate() != null) {
            order.setLastPickupDate(req.lastPickupDate());
        }

        if (req.deliveryLocation() != null) {
            order.setDeliveryLocation(req.deliveryLocation());
        }

        if (req.deliveryDate() != null) {
            order.setDeliveryDate(req.deliveryDate());
        }

        if (req.deliveryTime() != null) {
            order.setDeliveryTime(req.deliveryTime());
        }

        if (req.returnYard() != null) {
            order.setReturnYard(req.returnYard());
        }

        if (req.returnDate() != null) {
            order.setReturnDate(req.returnDate());
        }

        if (req.note() != null) {
            order.setNote(req.note());
        }

        // 使用安全轉換
        if (req.status() != null) {
            try {
                order.setStatus(OrderStatusEnum.valueOf(req.status()));
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("無效的狀態：" + req.status());
            }

        }
        if (req.updatedBy() != null) {
            order.setUpdatedBy(req.updatedBy());
        }
        order.setUpdatedTime(ZonedDateTime.now());
    }

}
