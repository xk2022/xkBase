package com.xk.tom.domain.model.aggreate;

import com.xk.common.base.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.sql.Date;
import java.sql.Time;
import java.time.ZonedDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "Import_Order")
@Entity
public class ImportOrderAggreate extends OrderBasicAggreate {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "importId", updatable = false, nullable = false)
    @Comment("流水號") // 描述
    private Long importId;

    @Column(name = "orderId", nullable = false)
    @Comment("格式：yyyyMMdd-流水號")
    private String orderId;

    /**
     * 進口日期
     */
    @Column(name = "import_date")
    @Comment("進口日期")
    private ZonedDateTime importDate;

    /**
     * OrderRecord
     */
    @Column(name = "order_recordId")
    @Comment("記錄訂單")
    private Long orderRecordId;

    /**
     * 提貨單位置
     */
    @Column(name = "delivery_order_location")
    @Comment("提貨單位置")
    private String deliveryOrderLocation;

    /**
     * 船公司
     */
    @Column(name = "shipping_company")
    @Comment("船公司")
    private String shippingCompany;

    /**
     * 船名/航次
     */
    @Column(name = "vessel_voyage")
    @Comment("船名/航次")
    private String vesselVoyage;

    /**
     * 櫃號
     */
    @Column(name = "container_number")
    @Comment("櫃號")
    private String containerNumber;

    /**
     * 櫃型
     */
    @Column(name = "container_type")
    @Comment("櫃型")
    private String containerType;

    /**
     * 櫃場
     */
    @Column(name = "container_yard")
    @Comment("櫃場")
    private String containerYard;

    /**
     * 領櫃期限
     */
    @Column(name = "last_pickup_date")
    @Comment("領櫃期限")
    private ZonedDateTime lastPickupDate;

    /**
     * 送貨地點
     */
    @Column(name = "delivery_location")
    @Comment("送貨地點")
    private String deliveryLocation;

    /**
     * 送貨日期
     */
    @Column(name = "delivery_date")
    @Comment("送貨日期")
    private Date deliveryDate;

    /**
     * 送貨時間
     */
    @Column(name = "delivery_time")
    @Comment("送貨時間")
    private Time deliveryTime;

    /**
     * 還櫃地點
     */
    @Column(name = "return_yard")
    @Comment("還櫃地點")
    private String returnYard;

    /**
     * 還櫃日期
     */
    @Column(name = "return_date")
    @Comment("還櫃日期")
    private Date returnDate;

    /**
     * 還櫃時間
     */
    @Column(name = "return_time")
    @Comment("還櫃時間")
    private Time returnTime;

    /**
     * 備註
     */
    @Column(name = "note")
    @Comment("備註")
    private String note;
}
