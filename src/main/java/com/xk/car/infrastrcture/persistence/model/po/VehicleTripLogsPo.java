package com.xk.car.infrastrcture.persistence.model.po;

import com.xk.common.base.SoftDeletableEntity;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.*;
import org.hibernate.type.SqlTypes;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

/**
 * 車輛里程
 * create by hank
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "vehicle_trip_logs")
@Entity
@SQLDelete(sql = "UPDATE vehicle_trip_logs  SET deleted = 1, delete_time = NOW() WHERE uuid = ?")
public class VehicleTripLogsPo extends SoftDeletableEntity implements Serializable  {


    @Id
    @GeneratedValue
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "uuid", nullable = false, updatable = false, unique = true, length = 36)
    private UUID uuid;

    @Column(name = "car_id", nullable = false)
    @Comment("關聯車輛")
    private String carId;

    @Column(name = "order_id", nullable = false)
    @Comment("對應派車單編號")
    private String orderId;


    @Column(name = "start_mileage", nullable = false)
    @Comment("出發里程")
    private BigDecimal startMileage;


    @Column(name = "end_mileage", nullable = false)
    @Comment("返回里程")
    private BigDecimal endMileage;

    @Column(name = "distance", nullable = false)
    @Comment("總行駛距離(系統計算)")
    private BigDecimal distance;


    @Column(name = "date", nullable = false)
    @Comment("行駛日期")
    private Date date;

}
