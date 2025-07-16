package com.xk.tom.application.model;

import com.xk.exapmleFolder.domain.model.demo.OrderStatusEnum;

import java.sql.Date;
import java.sql.Time;
import java.time.ZonedDateTime;

public record ImportOrderRequestDTO (

         ZonedDateTime importDate,
         String deliveryOrderLocation,
         String shippingCompany,
         String vesselVoyage,
         String containerNumber,
         String containerType,
         String containerYard,
         ZonedDateTime lastPickupDate,
         String deliveryLocation,
         Date deliveryDate,
         Time deliveryTime,
         String returnYard,
         Date returnDate,
         Time returnTime,
         String note,
         String status,
         String updatedBy,
         ZonedDateTime updatedTime
){
}
