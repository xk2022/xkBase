package com.xk.tom.application.model;

import com.xk.exapmleFolder.domain.model.demo.OrderStatusEnum;
import com.xk.tom.domain.model.aggreate.OrderTypeEnum;

import java.sql.Date;
import java.sql.Time;
import java.time.ZonedDateTime;

public class ExportOrderResponseDTO {


    private Long exportId;
    private String orderId;
    private ZonedDateTime exportDate;
    private Long orderRecordId;
    private String shippingCompany;
    private String vesselVoyage;
    private ZonedDateTime clearanceDate;
    private String pickupCode;
    private String containerType;
    private String pickupYard;
    private String containerNumber;
    private String deliveryYard;
    private String loadingLocation;
    private Date loadingDate;
    private Time loadingTime;
    private String note;
    private OrderTypeEnum orderType;
    private Long customerId;
    private OrderStatusEnum status;
    private String createdBy;
    private ZonedDateTime createdTime;
    private String updatedBy;
    private ZonedDateTime updatedTime;


}
