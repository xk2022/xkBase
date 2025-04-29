package com.xk.tom.application.model;

import com.xk.tom.domain.model.aggreate.OrderId;

import java.sql.Date;
import java.sql.Time;
import java.time.ZonedDateTime;

public class OrderResponseDTO {

    private Long id;

    private OrderId orderId;

    private ZonedDateTime ImportDate;//進口日期

    private Long orderRecordId;//記錄訂單

    private String deliveryOrderLocation;//提貨單位置

    private String ShippingCompany;//船公司

    private String VesselVoyage;//船名/航次

    private String ContainerNumber;//櫃號

    private String ContainerType;//櫃型

    private String ContainerYard;//櫃場

    private ZonedDateTime LastPickupDate;//領櫃期限

    private String DeliveryLocation;//送貨地點

    private Date DeliveryDate;//送貨日期

    private Time DeliveryTime;//送貨時間

    private String ReturnYard;//還櫃地點

    private Date ReturnDate;//還櫃日期

    private Time ReturnTime;//還櫃時間

    private String Note;//備註

    private String orderType;//訂單類型

    private Long customerId;//客戶 ID

    private String status;//訂單狀態

    private String createdBy;//創建者

    private ZonedDateTime createdTime;//創建時間

    private String updatedBy;//編輯者

    private ZonedDateTime updatedTime;//編輯時間
//    --------------------------出口-------------------------
    private ZonedDateTime ExportDate;//出口日期

    private ZonedDateTime clearanceDate;//結關日

    private String pickupCode; // 領櫃代號

    private String pickupYard;//領櫃場

    private String deliveryyYard;//交櫃場

    private String loadingLocation;//上貨地點

    private Date loadingDate;//上貨日期

    private Time loadingTime;//上貨時間
}
