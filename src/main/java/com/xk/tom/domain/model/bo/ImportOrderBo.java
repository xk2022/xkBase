package com.xk.tom.domain.model.bo;

import com.xk.tom.domain.model.enums.OrderStatus;
import com.xk.tom.domain.model.enums.OrderType;
import lombok.*;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.UUID;

/**
 * 📌 ImportOrderBo
 * - 用於 Service / UseCase 傳輸資料
 * - 不包含業務邏輯
 *
 * @author hank Created on 2025/04/29
 * @author yuan Updated on 2025/08/04.
 */
@Data
public class ImportOrderBo {

    private UUID uuid;
    private Long importId;
    private String orderId;
    private ZonedDateTime importDate;//進口日期
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
    private OrderType orderType;//訂單類型
    private Long customerId;//客戶 ID
    private OrderStatus status;//訂單狀態
    private String createdBy;//創建者
    private ZonedDateTime createdTime;//創建時間
    private String updatedBy;//編輯者
    private ZonedDateTime updatedTime;//編輯時間
    private String contactPerson;//聯絡人姓名
    private LocalDate shipDate;

}
