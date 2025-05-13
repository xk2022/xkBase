package com.xk.tom.application.model;


import com.xk.exapmleFolder.domain.model.demo.OrderStatusEnum;
import com.xk.tom.domain.model.aggreate.OrderTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.sql.Time;
import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ImportOrderResponseDTO {

    private Long importId;

    private String orderId;

    private ZonedDateTime ImportDate;

    private Long orderRecordId;

    private String deliveryOrderLocation;

    private String ShippingCompany;

    private String VesselVoyage;

    private String ContainerNumber;

    private String ContainerType;

    private String ContainerYard;

    private ZonedDateTime LastPickupDate;

    private String DeliveryLocation;

    private Date DeliveryDate;

    private Time DeliveryTime;

    private String ReturnYard;

    private Date ReturnDate;

    private Time ReturnTime;

    private String Note;

    private OrderTypeEnum orderType;

    private Long customerId;

    private OrderStatusEnum status;

    private String createdBy;

    private ZonedDateTime createdTime;

    private String updatedBy;

    private ZonedDateTime updatedTime;
}
