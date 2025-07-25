package com.xk.tom.application.model;

import java.sql.Date;
import java.time.ZonedDateTime;

public record ExportOrderRequestDTO(

        ZonedDateTime exportDate,
        String shippingCompany,
        String vesselVoyage,
        ZonedDateTime clearanceDate,
        String pickupCode,
        String containerType,
        String pickupYard,
        String containerNumber,
        String deliveryYard,
        String loadingLocation,
        Date loadingDate,
        String note,
        String orderType,
        String status,
        String updatedBy

) {
}
