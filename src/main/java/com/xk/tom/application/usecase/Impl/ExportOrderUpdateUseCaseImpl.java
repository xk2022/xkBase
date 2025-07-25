package com.xk.tom.application.usecase.Impl;

import com.xk.common.util.XkBeanUtils;
import com.xk.exapmleFolder.domain.model.demo.OrderStatusEnum;
import com.xk.tom.application.model.ExportOrderRequestDTO;
import com.xk.tom.application.model.ExportOrderResponseDTO;
import com.xk.tom.application.model.ImportOrderRequestDTO;
import com.xk.tom.application.model.ImportOrderResponseDTO;
import com.xk.tom.application.usecase.ExportOrderUpdateUseCase;
import com.xk.tom.domain.model.aggreate.ExportOrderAggreate;
import com.xk.tom.domain.model.bo.ExportOrderBO;
import com.xk.tom.domain.service.ExportOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.time.ZonedDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExportOrderUpdateUseCaseImpl implements ExportOrderUpdateUseCase {

    private final ExportOrderService exportOrderService;

    @Override
    @Transactional
    public ExportOrderResponseDTO updateExportOrder(Long exportId, ExportOrderRequestDTO request)throws ParseException {
        ExportOrderAggreate exportOrderAggreate = exportOrderService.findByExportId(exportId)
                .orElseThrow(()-> new IllegalArgumentException("找不到對應的 ExportOrder ID :"+exportId));

        updateExportOrderFromRequest(exportOrderAggreate,request);

        // 儲存
        ExportOrderAggreate aggreate=  exportOrderService.update(exportOrderAggreate);


        return XkBeanUtils.copyProperties(aggreate, ExportOrderResponseDTO::new);
    }

    @Transactional
    public void updateExportOrderFromRequest(ExportOrderAggreate order ,ExportOrderRequestDTO req){
        if(req.exportDate()!=null){
            order.setExportDate(req.exportDate());
        }
        if(req.deliveryYard()!=null){
            order.setDeliveryYard(req.deliveryYard());
        }
        if(req.shippingCompany()!=null){
            order.setShippingCompany(req.shippingCompany());
        }
        if(req.vesselVoyage()!=null){
            order.setVesselVoyage(req.vesselVoyage());
        }
        if(req.clearanceDate()!=null){
            order.setClearanceDate(req.clearanceDate());
        }
        if(req.pickupCode()!=null){
            order.setPickupCode(req.pickupCode());
        }
        if(req.containerType()!=null){
            order.setContainerType(req.containerType());
        }
        if(req.pickupYard()!=null){
            order.setPickupYard(req.pickupYard());
        }
        if(req.containerNumber()!=null){
            order.setContainerNumber(req.containerNumber());
        }
        if(req.loadingLocation()!=null){
            order.setLoadingLocation(req.loadingLocation());
        }
        if(req.loadingDate()!=null){
            order.setLoadingDate(req.loadingDate());
        }
        if(req.note()!=null){
            order.setNote(req.note());
        }

        if (req.status() != null) {
            try {
                order.setStatus(OrderStatusEnum.valueOf(req.status()));
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("無效的狀態：" + req.status());
            }
        }


        if(req.updatedBy()!=null){
            order.setUpdatedBy(req.updatedBy());
        }
        order.setUpdatedTime(ZonedDateTime.now());
    }
}
