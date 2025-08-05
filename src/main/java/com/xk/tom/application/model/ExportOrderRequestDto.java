package com.xk.tom.application.model;

import lombok.Data;

import java.time.LocalDate;

/**
 * 📌 ExportOrderRequestDto
 *
 * @author yuan Created on 2025/08/05.
 */
@Data
public class ExportOrderRequestDto {
    private String vesselVoyage;
    private LocalDate shipDate;
}
//public record ExportOrderRequestDto(
//
//        ZonedDateTime exportDate,
//        String shippingCompany,
//        String vesselVoyage,
//        ZonedDateTime clearanceDate,
//        String pickupCode,
//        String containerType,
//        String pickupYard,
//        String containerNumber,
//        String deliveryYard,
//        String loadingLocation,
//        Date loadingDate,
//        String note,
//        String orderType,
//        String status,
//        String updatedBy
//
//) {
//}
