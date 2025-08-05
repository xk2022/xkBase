package com.xk.tom.application.model;


import com.xk.tom.domain.model.enums.OrderStatus;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

/**
 * 📌 ImportOrderResponseDto
 * - 回傳給前端的進口訂單資訊
 *
 * @author yuan Created on 2025/08/05.
 */
@Data
public class ImportOrderResponseDto {
    private UUID uuid;
    private OrderStatus status;
    private String containerNumber;
    private String deliveryLocation;
    private LocalDate shipDate;

//    private Long importId;
//    private String orderId;
//    private ZonedDateTime ImportDate;
//    private Long orderRecordId;
//    private String deliveryOrderLocation;
//    private String ShippingCompany;
//    private String VesselVoyage;
//    private String ContainerNumber;
//    private String ContainerType;
//    private String ContainerYard;
//    private ZonedDateTime LastPickupDate;
//    private String DeliveryLocation;
//    private Date DeliveryDate;
//    private Time DeliveryTime;
//    private String ReturnYard;
//    private Date ReturnDate;
//    private Time ReturnTime;
//    private String Note;
//    private OrderType orderType;
//    private Long customerId;
//    private OrderStatusEnum status;
//    private String createdBy;
//    private ZonedDateTime createdTime;
//    private String updatedBy;
//    private ZonedDateTime updatedTime;
}
