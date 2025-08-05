package com.xk.tom.infrastructure.persistence.model.po;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import java.time.ZonedDateTime;

/**
 * 📌 ExportOrderPo（出口訂單持久化對象，PO）
 * <p>
 * - 繼承自 {@link OrderPo}，對應單表繼承 `orders`
 * - 使用 `@DiscriminatorValue("export")` 區分訂單類型
 * - 提供出口訂單專屬欄位：
 * - 出口日期（shipDate）
 * - 船公司、船名/航次
 * - 結關日
 * - 領櫃代號、櫃型、領櫃場、櫃號
 * - 交櫃場
 * - 上貨地點、上貨日期、上貨時間
 * - 備註
 *
 * @author yuan Created on 2025/08/04.
 */
@Getter
@Setter
@Entity
@DiscriminatorValue("export")
public class ExportOrderPo extends OrderPo {

    @Column(name = "ship_date")
    @Comment("出口日期")
    private ZonedDateTime shipDate;

    @Column(name = "shipping_company")
    @Comment("船公司")
    private String shippingCompany;

    @Column(name = "vessel_voyage")
    @Comment("船名/航次")
    private String vesselVoyage;

    @Column(name = "clearance_date")
    @Comment("結關日")
    private ZonedDateTime clearanceDate;

    @Column(name = "pickup_code")
    @Comment("領櫃代號")
    private String pickupCode;

    @Column(name = "container_type")
    @Comment("櫃型")
    private String containerType;

    @Column(name = "pickup_yard")
    @Comment("領櫃場")
    private String pickupYard;

    @Column(name = "container_number")
    @Comment("櫃號")
    private String containerNumber;

    @Column(name = "delivery_yard")
    @Comment("交櫃場")
    private String deliveryYard;

    @Column(name = "loading_location")
    @Comment("上貨地點")
    private String loadingLocation;

    @Column(name = "loading_date")
    @Comment("上貨日期")
    private ZonedDateTime loadingDate;

    @Column(name = "loading_time")
    @Comment("上貨時間")
    private ZonedDateTime loadingTime;

    @Column(name = "remark", columnDefinition = "TEXT")
    @Comment("備註")
    private String remark;

}
