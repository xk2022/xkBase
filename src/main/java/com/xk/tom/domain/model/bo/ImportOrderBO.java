package com.xk.tom.domain.model.bo;


import com.xk.tom.domain.model.aggreate.OrderId;
import lombok.*;

import java.sql.Date;
import java.sql.Time;
import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
public class ImportOrderBO {

    private OrderId orderId;

    private ZonedDateTime ImportDate;//進口日期

    private Long orderRecordId;//記錄訂單

    private String deliveryOrderLocation;//提貨單位置

    private String shippingCompany;//船公司

    private String vesselVoyage;//船名/航次

    private String containerNumber;//櫃號

    private String containerType;//櫃型

    private String containerYard;//櫃場

    private ZonedDateTime lastPickupDate;//領櫃期限

    private String deliveryLocation;//送貨地點

    private Date deliveryDate;//送貨日期

    private Time deliveryTime;//送貨時間

    private String returnYard;//還櫃地點

    private Date returnDate;//還櫃日期

    private Time returnTime;//還櫃時間

    private String note;//備註

    private String orderType;//訂單類型

    private Long customerId;//客戶 ID

    private String status;//訂單狀態

    private String createdBy;//創建者

    private ZonedDateTime createdTime;//創建時間

    private String updatedBy;//編輯者

    private ZonedDateTime updatedTime;//編輯時間

}
