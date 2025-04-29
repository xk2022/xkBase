package com.xk.tom.application.model;


import lombok.Data;

import java.sql.Date;
import java.sql.Time;
import java.time.ZonedDateTime;


public record OrderCreateDTO(

         ZonedDateTime importDate,//進口日期

         String deliveryOrderLocation,//提貨單位置

         String shippingCompany,//船公司

         String vesselVoyage,//船名/航次

         String containerNumber,//櫃號

         String containerType,//櫃型

         String containerYard,//櫃場

         ZonedDateTime lastPickupDate,//領櫃期限

         String deliveryLocation,//送貨地點

         Date deliveryDate,//送貨日期

         Time deliveryTime,//送貨時間

         String returnYard,//還櫃地點

         Date returnDate,//還櫃日期

         Time returnTime,//還櫃時間

         String note,//備註

         String orderType,//訂單類型import/export

         Long customerId,//客戶 ID

//--------------------出口-------------------------------------------------

        ZonedDateTime Exportdate,//出口日期

        // String ShippingCompany,//（船公司）(共有)
        // String vessel_voyage ,//（船名/航次）(共有）
         //String ContainerType(櫃型)(共有)
         //String container_number(共有)
         String pickupCode,//領櫃代號
         String pickupYard,//領櫃場
         String deliveryyYard,//交櫃場
         String loadingLocation,//上貨地點
         Date loadingDate ,//(上貨日期)
         Time loadingTime,//(上貨時間)
         //String note(共有)
         ZonedDateTime clearanceDate//結關日




) {
}
