package com.xk.tom.application.model;

import com.xk.exapmleFolder.domain.model.demo.OrderStatusEnum;
import lombok.Data;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.ZonedDateTime;

/**
 * 📌 ImportOrderRequestDto
 * - 前端傳入 → 建立/修改進口訂單
 *
 * @author yuan Created on 2025/08/05.
 */
@Data
public class ImportOrderRequestDto {
    private String containerNumber;
    private String deliveryLocation;
    private LocalDate shipDate;
}

//public record ImportOrderRequestDto(
//
//        ZonedDateTime importDate,
//        String deliveryOrderLocation,
//        String shippingCompany,
//        String vesselVoyage,
//        String containerNumber,
//        String containerType,
//        String containerYard,
//        ZonedDateTime lastPickupDate,
//        String deliveryLocation,
//        Date deliveryDate,
//        Time deliveryTime,
//        String returnYard,
//        Date returnDate,
//        Time returnTime,
//        String note,
//        String status,
//        String updatedBy,
//        ZonedDateTime updatedTime
//) {
//}
