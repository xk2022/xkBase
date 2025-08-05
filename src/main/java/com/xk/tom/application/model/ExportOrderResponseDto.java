package com.xk.tom.application.model;

import com.xk.tom.domain.model.enums.OrderStatus;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

/**
 * 📌 ExportOrderResponseDto
 *
 * @author yuan Created on 2025/08/05.
 */
@Data
public class ExportOrderResponseDto {
    private UUID uuid;
    private OrderStatus status;
    private String vesselVoyage;
    private LocalDate shipDate;
}

//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//public class ExportOrderResponseDto {
//    private Long exportId;
//    private String orderId;
//    private ZonedDateTime exportDate;
//    private Long orderRecordId;
//    private String shippingCompany;
//    private String vesselVoyage;
//    private ZonedDateTime clearanceDate;
//    private String pickupCode;
//    private String containerType;
//    private String pickupYard;
//    private String containerNumber;
//    private String deliveryYard;
//    private String loadingLocation;
//    private Date loadingDate;
//    private Time loadingTime;
//    private String note;
//    private OrderType orderType;
//    private Long customerId;
//    private OrderStatusEnum status;
//    private String createdBy;
//    private ZonedDateTime createdTime;
//    private String updatedBy;
//    private ZonedDateTime updatedTime;
//}
