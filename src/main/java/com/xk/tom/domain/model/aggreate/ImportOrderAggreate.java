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
@Table(name="Import_Order")
public class ImportOrderAggreate extends OrderBasicAggreate{

    @EmbeddedId
    private OrderId  orderId;

    /**
     * 進口日期
     */
    @Column(name="ship_date")
    @Comment("進口日期")
    private ZonedDateTime ImportDate;

    /**
     * OrderRecord
     */
    @Column(name="order_recordId")
    @Comment("記錄訂單")
    private Long orderRecordId;

    /**
     * 提貨單位置
     */
    @Column(name="delivery_order_location")
    @Comment("提貨單位置")
    private String deliveryOrderLocation;

    /**
     * 船公司
     */
    @Column(name="shipping_company")
    @Comment("船公司")
    private String ShippingCompany;

    /**
     * 船名/航次
     */
    @Column(name="vessel_voyage")
    @Comment("船名/航次")
    private String VesselVoyage;

    /**
     * 櫃號
     */
    @Column(name="container_number")
    @Comment("櫃號")
    private String ContainerNumber;

    /**
     * 櫃型
     */
    @Column(name="container_type")
    @Comment("櫃型")
    private String ContainerType;

    /**
     * 櫃場
     */
    @Column(name="container_yard")
    @Comment("櫃場")
    private String ContainerYard;

    /**
     * 領櫃期限
     */
    @Column(name="last_pickup_date")
    @Comment("領櫃期限")
    private ZonedDateTime LastPickupDate;

    /**
     * 送貨地點
     */
    @Column(name="delivery_location")
    @Comment("送貨地點")
    private String DeliveryLocation;

    /**
     * 送貨日期
     */
    @Column(name="delivery_date")
    @Comment("送貨日期")
    private Date DeliveryDate;

    /**
     * 送貨時間
     */
    @Column(name="delivery_time")
    @Comment("送貨時間")
    private Time DeliveryTime;

    /**
     * 還櫃地點
     */
    @Column(name="return_yard")
    @Comment("還櫃地點")
    private String ReturnYard;

    /**
     * 還櫃日期
     */
    @Column(name="return_date")
    @Comment("還櫃日期")
    private Date ReturnDate;

    /**
     * 還櫃時間
     */
    @Column(name="return_time")
    @Comment("還櫃時間")
    private Time ReturnTime;

    /**
     * 備註
     */
    @Column(name="note")
    @Comment("備註")
    private String Note;
}
