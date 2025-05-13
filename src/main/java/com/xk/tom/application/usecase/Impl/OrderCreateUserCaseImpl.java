package com.xk.tom.application.usecase.Impl;

import com.xk.common.util.XkBeanUtils;
import com.xk.exapmleFolder.domain.model.demo.OrderStatusEnum;
import com.xk.tom.application.model.OrderCreateDTO;
import com.xk.tom.application.model.OrderResponseDTO;
import com.xk.tom.application.usecase.OrderCreateUseCase;
import com.xk.tom.domain.model.aggreate.CustomerAggreate;
import com.xk.tom.domain.model.aggreate.OrderTypeEnum;
import com.xk.tom.domain.model.bo.ExportOrderBO;
import com.xk.tom.domain.model.bo.ImportOrderBO;
import com.xk.tom.domain.model.bo.OrderRecordBO;
import com.xk.tom.domain.service.CustomerService;
import com.xk.tom.domain.service.ExportOrderService;
import com.xk.tom.domain.service.ImportOrderService;
import com.xk.tom.domain.service.OrderRecordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Optional;


@Slf4j
@Service
@RequiredArgsConstructor
public class OrderCreateUserCaseImpl implements OrderCreateUseCase {


    private final ImportOrderService importOrderService;
    private final ExportOrderService exportOrderService;
    private final OrderRecordService orderRecordService;
    private final CustomerService customerService;


    @Override
    public OrderResponseDTO create(OrderCreateDTO order) throws ParseException {
        OrderResponseDTO orderResponseDTO = new OrderResponseDTO();

        if(order.orderType().equals(OrderTypeEnum.IMPORT.name())) {
            //進口
            ImportOrderBO importOrderBO = XkBeanUtils.copyProperties(order ,ImportOrderBO::new);
            OrderRecordBO orderRecordBO = new OrderRecordBO();
            orderRecordBO.setIsImport(true);
            orderRecordBO.setIsExport(false);
            //訂單記錄
            OrderRecordBO savedOrderRecordBo =  orderRecordService.save(orderRecordBO);

            //客戶訊息
            Optional<CustomerAggreate> customerAggreateOptional =customerService.findByContactPerson(importOrderBO.getContactPerson());
            customerAggreateOptional.ifPresent(customerAggreate -> importOrderBO.setCustomerId(customerAggreate.getCustomerId()));

            //訂單狀態預設pending
            importOrderBO.setStatus(OrderStatusEnum.PENDING);
            //訂單類型
            importOrderBO.setOrderType(OrderTypeEnum.IMPORT);
            //訂單記錄
            importOrderBO.setOrderRecordId(savedOrderRecordBo.getOrderRecordId());

            ImportOrderBO savedImportOrderBO =importOrderService.save(importOrderBO);

            orderResponseDTO = XkBeanUtils.copyProperties(savedImportOrderBO ,OrderResponseDTO::new );
            orderResponseDTO.setOrderType(savedImportOrderBO.getOrderType().name());
            orderResponseDTO.setStatus(savedImportOrderBO.getStatus().name());

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
            exportOrderBO.setStatus(OrderStatusEnum.PENDING);
            //訂單類型
            exportOrderBO.setOrderType(OrderTypeEnum.EXPORT);

            //客戶訊息
            Optional<CustomerAggreate> customerAggreateOptional =customerService.findByContactPerson(exportOrderBO.getContactPerson());
            customerAggreateOptional.ifPresent(customerAggreate -> exportOrderBO.setCustomerId(customerAggreate.getCustomerId()));

            ExportOrderBO savedExportOrderBO = exportOrderService.save(exportOrderBO);
            orderResponseDTO = XkBeanUtils.copyProperties(savedExportOrderBO ,OrderResponseDTO::new );
            orderResponseDTO.setOrderType(savedExportOrderBO.getOrderType().name());
            orderResponseDTO.setStatus(savedExportOrderBO.getStatus().name());
            return orderResponseDTO;
        }

    }



}
