package com.xk.tom.application.usecase.Impl;

import com.xk.common.util.XkBeanUtils;
import com.xk.tom.application.model.OrderCreateDTO;
import com.xk.tom.application.model.OrderResponseDTO;
import com.xk.tom.application.usecase.ImportOrderCreateUseCase;
import com.xk.tom.domain.model.aggreate.ImportOrderAggreate;
import com.xk.tom.domain.model.bo.ExportOrderBO;
import com.xk.tom.domain.model.bo.ImportOrderBO;
import com.xk.tom.domain.model.bo.OrderRecordBO;
import com.xk.tom.domain.service.ExportOrderService;
import com.xk.tom.domain.service.ImportOrderService;
import com.xk.tom.domain.service.OrderRecordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class ImportOrderCreateUserCaseImpl implements ImportOrderCreateUseCase {


    private final ImportOrderService importOrderService;
    private final ExportOrderService exportOrderService;
    private final OrderRecordService orderRecordService;


    @Override
    public OrderResponseDTO create(OrderCreateDTO order) throws ParseException {
        OrderResponseDTO orderResponseDTO = new OrderResponseDTO();

        if(order.orderType().equals("import")) {
            //進口
            ImportOrderBO importOrderBO = XkBeanUtils.copyProperties(order ,ImportOrderBO::new);
            OrderRecordBO orderRecordBO = new OrderRecordBO();
            orderRecordBO.setIsImport(true);
            orderRecordBO.setIsExport(false);
            //訂單記錄
            OrderRecordBO savedOrderRecordBo =  orderRecordService.save(orderRecordBO);

            //TODO客戶訊息
//            importOrderBO.setCustomerId(1L);

            //訂單狀態預設pending
            importOrderBO.setStatus("PENDING");
            //訂單記錄
            importOrderBO.setOrderRecordId(savedOrderRecordBo.getOrderRecordId());

            ImportOrderBO savedImportOrderBO =importOrderService.save(importOrderBO);
             orderResponseDTO = XkBeanUtils.copyProperties(savedImportOrderBO ,OrderResponseDTO::new );

            return orderResponseDTO;

        }else{
            //出口
            ExportOrderBO exportOrderBO = XkBeanUtils.copyProperties(order ,ExportOrderBO::new);
            OrderRecordBO orderRecordBO = new OrderRecordBO();
            orderRecordBO.setIsImport(false);
            orderRecordBO.setIsExport(true);

            //訂單記錄
            OrderRecordBO savedOrderRecordBo =  orderRecordService.save(orderRecordBO);
            exportOrderBO.setOrderRecordId(savedOrderRecordBo.getOrderRecordId());

            //訂單狀態 預設pending
            exportOrderBO.setStatus("PENDING");

            //TODO客戶訊息

            ExportOrderBO savedExportOrderBO = exportOrderService.save(exportOrderBO);
             orderResponseDTO = XkBeanUtils.copyProperties(savedExportOrderBO ,OrderResponseDTO::new );
            return orderResponseDTO;
        }

    }



}
