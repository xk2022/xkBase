package com.xk.tom.domain.model.bo;

import com.xk.tom.domain.model.enums.OrderStatus;
import com.xk.tom.domain.model.enums.OrderType;
import lombok.Data;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.UUID;

/**
 * 📌 ExportOrderBo
 *
 * @author yuan Created on 2025/08/05.
 */
@Data
public class ExportOrderBo {

    private UUID uuid;
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
    private OrderType orderType;
    private Long customerId;
    private OrderStatus status;
    private String createdBy;
    private ZonedDateTime createdTime;
    private String updatedBy;
    private ZonedDateTime updatedTime;
    private String contactPerson;
    private LocalDate shipDate;

}
