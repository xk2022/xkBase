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
@Table(name="Export_Order")
public class ExportOrderAggreate extends OrderBasicAggreate{

    @EmbeddedId
    private OrderId  orderId;


    /**
     * 出口日期
     */
    @Column(name="ship_date")
    @Comment("出口日期")
    private ZonedDateTime exportDate;

    /**
     * OrderRecord
     */
    @Column(name="order_recordId")
    @Comment("記錄訂單")
    private Long orderRecordId;


    /**
     *船公司
     */
    @Column(name="shipping_company")
    @Comment("船公司")
    private String shippingCompany;

    /**
     * 船名/航次
     */
    @Column(name="vessel_voyage")
    @Comment("船名/航次")
    private String vesselVoyage;

    /**
     * 結關日
     */
    @Column(name="clearance_date")
    @Comment("結關日")
    private ZonedDateTime clearanceDate;

    /**
     *領櫃代號
     */
    @Column(name="pickup_code")
    @Comment("領櫃代號")
    private String pickupCode;

    /**
     * 櫃型
     */
    @Column(name="container_type")
    @Comment("櫃型")
    private String containerType;

    /**
     *領櫃場
     */
    @Column(name="pickup_yard")
    @Comment("領櫃場")
    private String pickupYard;

    /**
     * 櫃號
     */
    @Column(name="container_number")
    @Comment("櫃號")
    private String containerNumber;

    /**
     * 交櫃場
     */
    @Column(name="delivery_yard")
    @Comment("交櫃場")
    private String deliveryYard;


    /**
     * 上貨地點
     */
    @Column(name="loading_location")
    @Comment("上貨地點")
    private String loadingLocation;

    /**
     * 上貨日期
     */
    @Column(name="loading_date")
    @Comment("上貨日期")
    private Date loadingDate;

    /**
     *上貨時間
     */
    @Column(name="loading_time")
    @Comment("上貨時間")
    private Time loadingTime;

    /**
     * 備註
     */
    @Column(name="note")
    @Comment("備註")
    private String note;


}
