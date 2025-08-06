package com.xk.tom.infra.persistence.model.po;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * 📌 ImportOrderPo（進口訂單持久化對象，PO）
 * <p>
 * - 繼承自 {@link OrderPo}，對應單表繼承 `orders`
 * - 使用 `@DiscriminatorValue("import")` 區分訂單類型
 * - 提供進口訂單專屬欄位：
 * - 進口日期（shipDate）
 * - 船公司、船名/航次
 * - 櫃號、櫃型、櫃場
 * - 領櫃期限
 * - 提貨單號 (DO)、提貨單位置
 * - 送貨地點、送貨日期、送貨時間
 * - 還櫃地點、還櫃日期、還櫃時間
 * - 備註
 * <p>
 *
 * @author yuan Created on 2025/08/04.
 */
@Getter
@Setter
@Entity
@DiscriminatorValue("import")
public class ImportOrderPo extends OrderPo {

    @Column(name = "ship_date")
    @Comment("進口日期")
    private LocalDate shipDate;

    @Column(name = "shipping_company")
    @Comment("船公司")
    private String shippingCompany;

    @Column(name = "vessel_voyage")
    @Comment("船名/航次")
    private String vesselVoyage;

    @Column(name = "container_number")
    @Comment("櫃號")
    private String containerNumber;

    @Column(name = "container_type")
    @Comment("櫃型")
    private String containerType;

    @Column(name = "container_yard")
    @Comment("櫃場")
    private String containerYard;

    @Column(name = "last_pickup_date")
    @Comment("領櫃期限")
    private LocalDate lastPickupDate;

    @Column(name = "do_number")
    @Comment("提貨單號 (DO)")
    private String doNumber;

    @Column(name = "do_location")
    @Comment("提貨單位置")
    private String doLocation;

    @Column(name = "delivery_location")
    @Comment("送貨地點")
    private String deliveryLocation;

    @Column(name = "delivery_date")
    @Comment("送貨日期")
    private LocalDate deliveryDate;

    @Column(name = "delivery_time")
    @Comment("送貨時間")
    private LocalTime deliveryTime;

    @Column(name = "return_yard")
    @Comment("還櫃地點")
    private String returnYard;

    @Column(name = "return_date")
    @Comment("還櫃日期")
    private LocalDate returnDate;

    @Column(name = "return_time")
    @Comment("還櫃時間")
    private LocalTime returnTime;

    @Column(name = "remark", columnDefinition = "TEXT")
    @Comment("備註")
    private String remark;

}
